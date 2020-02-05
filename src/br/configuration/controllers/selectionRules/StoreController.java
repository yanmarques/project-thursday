package br.configuration.controllers.selectionRules;

import br.common.RandomString;
import br.common.models.selectionRuleSubjects.SelectionRuleSubject;
import br.common.models.selectionRuleSubjects.SelectionRuleSubjectDAO;
import br.common.models.selectionRules.SelectionRule;
import br.common.models.selectionRules.SelectionRuleDAO;
import br.common.models.subject.SubjectDAO;
import br.common.validation.RuleNames;
import br.common.validation.RuleWrapper;
import br.common.validation.textValues.ComboBoxValue;
import br.common.validation.textValues.CountTableItem;
import br.configuration.Main;
import br.configuration.controllers.subject.SubjectView;
import br.configuration.interactions.controller.DatabaseInteraction;
import br.configuration.interactions.database.Action;
import br.configuration.interactions.database.RelationsInteractiveController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.function.Function;

public class StoreController extends RelationsInteractiveController<SelectionRule, SelectionRuleView, SelectionRuleDAO,
        SelectionRuleSubject, SelectionRuleSubjectView, SelectionRuleSubjectDAO> {
    @FXML
    TextField imagePath;

    @FXML
    protected ImageView imageView;

    @FXML
    protected ComboBox<SubjectView> subjectsBox;

    @FXML
    protected TableView<SelectionRuleSubjectView> subjectsView;

    @FXML
    private TableColumn<SelectionRuleSubjectView, String> nameColumn;

    @Override
    protected Node getOwner() {
        return this.imageView;
    }

    @Override
    protected TableView<SelectionRuleSubjectView> getTable() {
        return this.subjectsView;
    }

    @Override
    protected SelectionRuleSubjectDAO getNewlyDatabase() {
        return new SelectionRuleSubjectDAO();
    }

    @Override
    protected Function<SelectionRuleSubject, SelectionRuleSubjectView> getSyncerFunction() {
        return SelectionRuleSubjectView::new;
    }

    @Override
    protected SelectionRuleSubject getRelationModel() {
        return new SelectionRuleSubject();
    }

    @Override
    protected void fillRelationModel(SelectionRuleSubject model) {
        model.setSubject(this.subjectsBox.getSelectionModel().getSelectedItem().getModel());
    }

    @Override
    protected void fillRelation(SelectionRule model, SelectionRuleSubject relation) {
        relation.setSelectionRule(model);
    }

    @Override
    protected boolean isCreateValid() {
        return ! this.subjectsBox.getSelectionModel().isEmpty();
    }

    @Override
    protected void fillModel(SelectionRule model) {
        model.setImagePath(this.imagePath.getText());
    }

    @Override
    protected SelectionRule getModel() {
        return new SelectionRule();
    }

    @Override
    protected Action getAction() {
        return Action.STORE;
    }

    @Override
    public void initialize() {
        super.initialize();
        new DatabaseInteraction<>(new SubjectDAO(), this.subjectsBox, SubjectView::new).syncAll();
        this.registerRules();
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
    }

    @FXML
    public void openImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolha a imagem");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Images", "*.*"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(this.getOwner().getScene().getWindow());
        this.renderImage(file);
    }

    protected void renderImage(File file) {
        if (file != null && file.exists() && file.canRead()) {
            try {
                this.imageView.setImage(new Image(new FileInputStream(file)));
                this.imageView.setFitWidth(800);
                this.imagePath.setText(file.getAbsolutePath());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    protected boolean handleSave(SelectionRule model) {
        File file = new File(model.getImagePath());
        File destFile = this.getNewImageDestiny(file);

        if (this.copyFile(file, destFile)) {
            model.setImagePath(destFile.getPath());
            return super.handleSave(model);
        }

        return false;
    }

    private void registerRules() {
        RuleWrapper requiredWrapper = this.getValidator().createWraper(RuleNames.REQUIRED);

        requiredWrapper.createRule(this.imagePath, "imagem");

        requiredWrapper.createRule(new CountTableItem<>(this.subjectsView), "matérias")
                .add(RuleNames.POSITIVE);
    }

    private boolean copyFile(File source, File dest) {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;

        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        } finally {
            try {
                if (sourceChannel != null) {
                    sourceChannel.close();
                }

                if (destChannel != null) {
                    destChannel.close();
                }
            } catch (IOException exception) { }
        }

        return true;
    }

    private File getNewImageDestiny(File source) {
        String[] fileSplitted = source.getName().split("\\.");
        String extension = fileSplitted[fileSplitted.length - 1];

        String newName = RandomString.generate(16) + "." + extension;

        String path = Main.getConfig().getImagesPath();

        if (! path.endsWith("/")) {
            path += "/";
        }

        return new File(path + newName);
    }
}
