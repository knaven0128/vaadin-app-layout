package com.github.appreciated.app.layout.test.ui.annotated;

import com.github.appreciated.app.layout.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.design.AppLayoutDesign;
import com.github.appreciated.app.layout.builder.elements.builders.SubmenuBuilder;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.builder.factories.DefaultNavigationElementInfoProducer;
import com.github.appreciated.app.layout.component.MenuHeader;
import com.github.appreciated.app.layout.component.button.AppBarNotificationButton;
import com.github.appreciated.app.layout.test.view.annotated.basic.*;
import com.vaadin.annotations.*;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

import static com.github.appreciated.app.layout.builder.Section.HEADER;

@SpringUI
@PushStateNavigation // proper url paths
@Push
@Viewport("initial-scale=1, maximum-scale=1")
@Theme("demo")
@Title("App Layout Demo")
public class BasicAnnotatedUI extends UI {

    DefaultNotificationHolder notifications = new DefaultNotificationHolder();
    DefaultBadgeHolder badge = new DefaultBadgeHolder();

    public void init(VaadinRequest request) {
        setContent(AppLayout.getDefaultBuilder(Behaviour.LEFT_RESPONSIVE_HYBRID)
                .withTitle("App Layout Basic Annotated Test")
                .addToAppBar(new AppBarNotificationButton(notifications))
                .withNavigationElementInfoProducer(new DefaultNavigationElementInfoProducer())
                .withDesign(AppLayoutDesign.MATERIAL)
                .add(new MenuHeader("Version 1.0.3", new ThemeResource("logo.png")), HEADER)
                .add(badge, View1.class)
                .add(SubmenuBuilder.get("My Submenu", VaadinIcons.PLUS)
                        .add(View2.class)
                        .add(View3.class)
                        .add(View4.class)
                        .build())
                .add(View5.class)
                .add(View6.class)
                .build());
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = BasicAnnotatedUI.class)
    public static class Servlet extends VaadinServlet {
    }

}