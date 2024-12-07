package org.vaadin.example.admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("admin/dashboard")
public class AdminDashboardView extends VerticalLayout {

    public AdminDashboardView() {
        // Page title
        H1 title = new H1("Admin Dashboard");
        title.getStyle().set("text-align", "center");

        // Navigation bar
        HorizontalLayout navigationBar = createNavigationBar();

        // Main content area
        HorizontalLayout mainContent = new HorizontalLayout();
        mainContent.setWidthFull();

        // Left panel (analytics summary)
        VerticalLayout leftPanel = new VerticalLayout();
        leftPanel.setWidth("30%");
        leftPanel.add(createAnalyticsSummary());

        // Right panel (details and data grid)
        VerticalLayout rightPanel = new VerticalLayout();
        rightPanel.setWidth("70%");
        rightPanel.add(createMockDataGrid());

        mainContent.add(leftPanel, rightPanel);

        // Footer
        Div footer = new Div();
        footer.setText("© 2024 Your Company");
        footer.getStyle().set("text-align", "center").set("margin-top", "20px");

        // Add components to the main layout
        add(title, navigationBar, mainContent, footer);
    }

    // Navigation bar
    private HorizontalLayout createNavigationBar() {
        Button home = new Button("Home");
        Button dashboard = new Button("Dashboard");
        Button settings = new Button("Settings");
        HorizontalLayout navBar = new HorizontalLayout(home, dashboard, settings);
        navBar.setWidthFull();
        navBar.getStyle().set("background-color", "#f0f0f0").set("padding", "10px");
        return navBar;
    }

    // Analytics summary (mock data)
    private VerticalLayout createAnalyticsSummary() {
        VerticalLayout summary = new VerticalLayout();
        summary.add(new H1("Summary"));
        summary.add(new Div(new Div("Users: 500"), new Div("Active Sessions: 120"), new Div("Revenue: $15,000")));
        summary.getStyle().set("padding", "10px").set("background-color", "#fafafa");
        return summary;
    }

    // Mock data grid
    private Grid<ServiceData> createMockDataGrid() {
        Grid<ServiceData> grid = new Grid<>(ServiceData.class);
        grid.setItems(
                new ServiceData("Service A", "Healthy", 99.9),
                new ServiceData("Service B", "Stable", 95.0),
                new ServiceData("Service C", "Unstable", 85.5)
        );
        grid.setColumns("serviceName", "status", "uptime");
        return grid;
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
