package org.vaadin.example.dashboard;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@PageTitle("Dashboard")
@Route("admin")
public class DashboardView extends Div {

    private static final String BACKGROUND_COLOR_DARK = "#333";
    private static final String BACKGROUND_COLOR_LIGHT = "#f8f9fa";
    private static final String COLOR_WHITE = "white";

    public DashboardView() {
        addClassName("dashboard-view");
        setSizeFull();

        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();

        Component sidebar = createSidebar();
        Component mainContent = createMainContent();

        mainLayout.add(sidebar, mainContent);
        mainLayout.setFlexGrow(1, mainContent);

        add(createNavbar(), mainLayout, createFooter());
    }

    private Component createNavbar() {
        HorizontalLayout navbar = new HorizontalLayout();
        navbar.setWidthFull();
        navbar.setPadding(true);
        navbar.getStyle().set("background-color", BACKGROUND_COLOR_DARK);
        navbar.getStyle().set("color", COLOR_WHITE);

        H2 logo = new H2("Admin Dashboard");
        logo.getStyle().set("color", COLOR_WHITE);

        Button logoutButton = new Button("Logout", VaadinIcon.SIGN_OUT.create());
        logoutButton.addClickListener(e -> logout());
        logoutButton.getStyle().set("color", COLOR_WHITE);
        logoutButton.getElement().getThemeList().add("contrast");

        navbar.add(logo, logoutButton);
        navbar.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        return new Header(navbar);
    }

    private void logout() {
        // Add logout logic
        System.out.println("Logout clicked");
    }

    private Component createSidebar() {
        VerticalLayout sidebar = new VerticalLayout();
        sidebar.setWidth("250px");
        sidebar.setPadding(true);
        sidebar.getStyle().set("background-color", BACKGROUND_COLOR_LIGHT);

        Button dashboardButton = createSidebarButton("Dashboard", VaadinIcon.DASHBOARD);
        Button settingsButton = createSidebarButton("Settings", VaadinIcon.COG);
        Button profileButton = createSidebarButton("Profile", VaadinIcon.USER);

        sidebar.add(dashboardButton, settingsButton, profileButton);
        return sidebar;
    }

    private Button createSidebarButton(String text, VaadinIcon icon) {
        return new Button(text, icon.create());
    }

    private Component createMainContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(true);
        layout.setSizeFull();

        layout.add(createHighlightSection(), createServiceHealth(), createResponseTimes());
        return layout;
    }

    private Component createFooter() {
        HorizontalLayout footer = new HorizontalLayout();
        footer.setWidthFull();
        footer.setPadding(true);
        footer.getStyle().set("background-color", BACKGROUND_COLOR_DARK);
        footer.getStyle().set("color", COLOR_WHITE);
        footer.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        footer.add(new Span("© 2024 Your Company. All rights reserved."));
        return new Footer(footer);
    }

    private Component createHighlightSection() {
        HorizontalLayout highlights = new HorizontalLayout();
        highlights.setWidthFull();
        highlights.setPadding(true);
        highlights.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        highlights.add(
                createHighlight("Current users", "745", 33.7),
                createHighlight("View events", "54.6k", -112.45),
                createHighlight("Conversion rate", "18%", 3.9),
                createHighlight("Custom metric", "-123.45", 0.0)
        );

        return highlights;
    }

    private Component createHighlight(String title, String value, Double percentage) {
        String prefix = percentage > 0 ? "+" : percentage < 0 ? "-" : "±";
        String theme = percentage > 0 ? "success" : percentage < 0 ? "error" : "neutral";

        H2 header = new H2(title);
        Span valueSpan = new Span(value);
        valueSpan.addClassName("highlight-value");

        Icon trendIcon = percentage >= 0 ? VaadinIcon.ARROW_UP.create() : VaadinIcon.ARROW_DOWN.create();
        Span badge = new Span(trendIcon, new Span(prefix + percentage));
        badge.getElement().getThemeList().add("badge " + theme);

        VerticalLayout highlight = new VerticalLayout(header, valueSpan, badge);
        highlight.setSpacing(false);
        highlight.setPadding(false);

        return highlight;
    }

    private Component createServiceHealth() {
        Grid<ServiceHealth> grid = new Grid<>(ServiceHealth.class, false);
        grid.addColumn(ServiceHealth::status).setHeader("Status"); // Accessor method for status
        grid.addColumn(ServiceHealth::city).setHeader("City");
        grid.addColumn(ServiceHealth::input).setHeader("Input");
        grid.addColumn(ServiceHealth::output).setHeader("Output");

        grid.setItems(ServiceHealth.sampleData());
        return new VerticalLayout(new H2("Service health"), grid);
    }

    private Component createResponseTimes() {
        Grid<ResponseTime> grid = new Grid<>(ResponseTime.class, false);

        grid.addColumn(ResponseTime::system).setHeader("System");
        grid.addColumn(ResponseTime::time).setHeader("Response Time (ms)");

        grid.setItems(ResponseTime.sampleData());
        return new VerticalLayout(new H2("Response times"), grid);
    }

    // Static data classes with sample data
    public record ServiceHealth(String status, String city, int input, int output) {
        public static List<ServiceHealth> sampleData() {
            return List.of(
                    new ServiceHealth("EXCELLENT", "Berlin", 324, 1540),
                    new ServiceHealth("OK", "London", 311, 1320),
                    new ServiceHealth("FAILING", "New York", 300, 1219)
            );
        }
    }

    public record ResponseTime(String system, int time) {
        public static List<ResponseTime> sampleData() {
            return List.of(
                    new ResponseTime("System 1", 120),
                    new ResponseTime("System 2", 135),
                    new ResponseTime("System 3", 150)
            );
        }
    }
}
