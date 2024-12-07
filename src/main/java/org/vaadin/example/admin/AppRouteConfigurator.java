package org.vaadin.example.admin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

public class AppRouteConfigurator implements BeforeEnterObserver {

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // Check if the user is authenticated
        if (!isUserAuthenticated() && !event.getNavigationTarget().equals(LoginView.class)) {
            // Redirect to login page if not authenticated
            event.rerouteTo(LoginView.class);
        }
    }

    private boolean isUserAuthenticated() {
        // Your authentication logic here
        return false;
    }
}