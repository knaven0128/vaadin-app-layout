package com.github.appreciated.app.layout.component.applayout;

import com.github.appreciated.app.layout.navigation.UpNavigationHelper;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * Every AppLayout is supposed to derive from a Polymer Template
 */
@NpmPackage(value = "@polymer/app-layout", version = "3.1.0")
public abstract class AppLayout extends PolymerTemplate<TemplateModel> implements AppLayoutElementBase, HasStyle {

    public void toggleDrawer() {
        getDrawer().getElement().callFunction("toggle");
    }

    public void openDrawer() {
        getDrawer().getElement().callFunction("open");
    }

    public void closeDrawerIfNotPersistent() {
        getElement().callFunction("closeIfNotPersistent");
    }

    public void closeDrawer() {
        getDrawer().getElement().callFunction("close");
    }

    @EventHandler
    public void onUpNavigation() {
        if (getContentElement() != null && getContentElement() instanceof Component) {
            UpNavigationHelper.performUpNavigation((Class<? extends Component>) getContentElement().getClass());
        }
    }
}