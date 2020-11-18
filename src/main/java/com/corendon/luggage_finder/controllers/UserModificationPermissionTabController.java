/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import com.corendon.luggage_finder.Utils;
import com.corendon.luggage_finder.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeCell;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Peter Dimitrov
 */
public class UserModificationPermissionTabController implements Initializable {

    @FXML
    private TreeView<String> permissionTreeView;

    private ResourceBundle bundle;
    private User selectedUser = null;

    /**
     * Initialises the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;
    }
    
    public void generateTreeView() {
        if (selectedUser == null) {
            return;
        }
        
        TreeItem<String> screens = new TreeItem<>(bundle.getString("user_modification_permission_tree_item_screens"));
        screens.setExpanded(true);

        /* --- Home Screen --- */
        TreeItem<String> homeScreen = new TreeItem<>(bundle.getString("user_modification_permission_tree_item_home"));
        homeScreen.setExpanded(true);
        CheckBoxTreeItem<String> view = new CheckBoxTreeItem<>(bundle.getString("user_modification_permission_tree_choice_box_view"));
        view.setSelected(true);
        
        homeScreen.getChildren().add(view);
        //homeScreen.getChildren().add(new CheckBoxTreeItem<>("Bewerken"));
        screens.getChildren().add(homeScreen);
        /* --- End Home Screen --- */

        /* --- Search Screen --- */
        TreeItem<String> searchScreen = new TreeItem<>(bundle.getString("user_modification_permission_tree_item_search"));
        searchScreen.setExpanded(true);
        view = new CheckBoxTreeItem<>(bundle.getString("user_modification_permission_tree_choice_box_view"));
        view.setSelected(true);
        
        searchScreen.getChildren().add(view);
        //searchScreen.getChildren().add(new CheckBoxTreeItem<>("Bewerken"));
        screens.getChildren().add(searchScreen);
        /* --- End Search Screen --- */

        /* --- Register Screen --- */
        TreeItem<String> registerScreen = new TreeItem<>(bundle.getString("user_modification_permission_tree_item_register"));
        registerScreen.setExpanded(true);
        view = new CheckBoxTreeItem<>(bundle.getString("user_modification_permission_tree_choice_box_view"));
        view.setSelected(true);
        registerScreen.getChildren().add(view);
        
        CheckBoxTreeItem<String> edit = new CheckBoxTreeItem<>(bundle.getString("user_modification_permission_tree_choice_box_edit"));
        edit.setSelected(true);
        registerScreen.getChildren().add(edit);
        screens.getChildren().add(registerScreen);
        /* --- End Register Screen --- */
        
        /* --- Statistics Screen --- */
        TreeItem<String> statisticsScreen = new TreeItem<>(bundle.getString("user_modification_permission_tree_item_statistics"));
        statisticsScreen.setExpanded(true);
        view = new CheckBoxTreeItem<>(bundle.getString("user_modification_permission_tree_choice_box_view"));
        view.setSelected(selectedUser.getFunction().getId() != Utils.Permissions.MANAGER.getId());
        statisticsScreen.getChildren().add(view);
        
        edit = new CheckBoxTreeItem<>(bundle.getString("user_modification_permission_tree_choice_box_edit"));
        edit.setSelected(selectedUser.getFunction().getId() != Utils.Permissions.MANAGER.getId());
        statisticsScreen.getChildren().add(edit);
        screens.getChildren().add(statisticsScreen);
        /* --- End Statistics Screen --- */

        /* --- Users Screen --- */
        TreeItem<String> usersScreen = new TreeItem<>(bundle.getString("user_modification_permission_tree_item_users"));
        usersScreen.setExpanded(true);
        view = new CheckBoxTreeItem<>(bundle.getString("user_modification_permission_tree_choice_box_view"));
        view.setSelected(true);
        usersScreen.getChildren().add(view);
        
        edit = new CheckBoxTreeItem<>(bundle.getString("user_modification_permission_tree_choice_box_edit"));
        edit.setSelected(selectedUser.getFunction().getId() != Utils.Permissions.MANAGER.getId());
        usersScreen.getChildren().add(edit);
        screens.getChildren().add(usersScreen);
        /* --- End Users Screen --- */

        permissionTreeView.setEditable(false);

        permissionTreeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> param) {
                return new CheckBoxTreeCell<String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else if (!(getTreeItem() instanceof CheckBoxTreeItem)) {
                            setGraphic(null);
                        }

                        if (item != null) {
                            TreeItem<String> value = this.treeItemProperty().getValue();

                            this.disableProperty().unbind();
                            this.disableProperty().bind(value.leafProperty());
                        }
                    }
                };
            }
        });

        permissionTreeView.setRoot(screens);
    }

    @FXML
    void onSaveButtonAction(ActionEvent event) {
        System.out.println("Saved");
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
}
