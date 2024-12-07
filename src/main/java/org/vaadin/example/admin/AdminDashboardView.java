package org.vaadin.example.admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("admin/dashboard")
public class AdminDashboardView extends VerticalLayout implements BeforeEnterObserver {

    public AdminDashboardView() {
        // Page title
        H1 title = new H1("Admin Dashboard");
        title.getStyle().set("text-align", "center");
        title.addClassName("page-title");

        // Navigation bar
        HorizontalLayout navigationBar = createNavigationBar();

        // Main content area
        HorizontalLayout mainContent = new HorizontalLayout();
        mainContent.setWidthFull();

        // Left panel (analytics summary)
        VerticalLayout leftPanel = new VerticalLayout();
        leftPanel.addClassName("left-panel");
        leftPanel.setWidth("30%");
        leftPanel.add(createAnalyticsSummary());

        // Right panel (details and data grid)
        VerticalLayout rightPanel = new VerticalLayout();
        rightPanel.addClassName("right-panel");
        rightPanel.setWidth("70%");
        rightPanel.add(createStyledMockDataGrid());

        mainContent.add(leftPanel, rightPanel);

        // Logout button
        Button logoutButton = new Button("Logout", e -> logout());
        logoutButton.addClassName("logout-button");
        logoutButton.getStyle().set("align-self", "center");

        // Footer
        Div footer = new Div();
        footer.setText("© 2024 Your Company");
        footer.getStyle().set("text-align", "center").set("margin-top", "20px");

        // Add components to the main layout
        addClassName("dashboard-layout");
        add(title, navigationBar, mainContent, logoutButton, footer);
    }

    private void logout() {
        VaadinSession.getCurrent().close();
        getUI().ifPresent(ui -> ui.navigate(LoginView.class));
    }

    // Navigation bar
    private HorizontalLayout createNavigationBar() {
        Button home = new Button("Home");
        Button dashboard = new Button("Dashboard");
        Button settings = new Button("Settings");
        HorizontalLayout navBar = new HorizontalLayout(home, dashboard, settings);
        navBar.addClassName("navigation-bar");
        navBar.setWidthFull();
        return navBar;
    }

    // Analytics summary (styled)
    private VerticalLayout createAnalyticsSummary() {
        VerticalLayout summary = new VerticalLayout();
        summary.addClassName("analytics-summary");
        summary.add(new H5("Summary"));
        summary.add(new Div(new Span("Users: 500"), new Span("Active Sessions: 120"), new Span("Revenue: $15,000")));
        summary.getStyle().set("padding", "15px").set("background-color", "#f8f9fa").set("border-radius", "5px");
        return summary;
    }

    // Styled mock data grid
    private Grid<ServiceData> createStyledMockDataGrid() {
        Grid<ServiceData> grid = new Grid<>(ServiceData.class);
        grid.addClassName("styled-data-grid");
        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        grid.setItems(
                new ServiceData("Service A", "Healthy", 99.9),
                new ServiceData("Service B", "Stable", 95.0),
                new ServiceData("Service C", "Unstable", 85.5)
        );
        grid.setColumns("serviceName", "status", "uptime");
        return grid;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!isUserAuthenticated()) {
            event.rerouteTo(LoginView.class);
        }
    }

    private boolean isUserAuthenticated() {
        return VaadinSession.getCurrent().getAttribute("authenticatedUser") != null;
    }

    // Mock data class
    public static class ServiceData {
        private String serviceName;
        private String status;
        private double uptime;

        public ServiceData(String serviceName, String status, double uptime) {
            this.serviceName = serviceName;
            this.status = status;
            this.uptime = uptime;
        }

        public String getServiceName() {
            return serviceName;
        }

        public String getStatus() {
            return status;
        }

        public double getUptime() {
            return uptime;
        }
    }
}
