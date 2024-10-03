package org.vaadin.example.dashboard;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.List;

@PageTitle("Dashboard")
@Route("admin")
public class DashboardView extends Div {

    public DashboardView() {
        addClassName("dashboard-view");
        setSizeFull();

        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();

        mainLayout.add(createSidebar());

        mainLayout.add(createMainContent());
        mainLayout.setFlexGrow(1, createMainContent());

        add(createNavbar(), mainLayout, createFooter());
    }

    private Component createNavbar() {
        HorizontalLayout navbar = new HorizontalLayout();
        navbar.setWidthFull();
        navbar.setPadding(true);
        navbar.getStyle().set("background-color", "#333");
        navbar.getStyle().set("color", "white");

        H2 logo = new H2("Admin Dashboard");
        logo.getStyle().set("color", "white");

        Button logoutButton = new Button("Logout", VaadinIcon.SIGN_OUT.create());
        logoutButton.getStyle().set("color", "white");
        logoutButton.getElement().getThemeList().add("contrast");

        navbar.add(logo);
        navbar.add(logoutButton);
        navbar.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        return new Header(navbar);
    }

    private Component createSidebar() {
        VerticalLayout sidebar = new VerticalLayout();
        sidebar.setWidth("250px");
        sidebar.setPadding(true);
        sidebar.getStyle().set("background-color", "#f8f9fa");

        Button dashboardButton = new Button("Dashboard", VaadinIcon.DASHBOARD.create());
        Button settingsButton = new Button("Settings", VaadinIcon.COG.create());
        Button profileButton = new Button("Profile", VaadinIcon.USER.create());

        sidebar.add(dashboardButton, settingsButton, profileButton);
        sidebar.setSpacing(true);
        return sidebar;
    }

    private Component createMainContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(true);
        layout.setSizeFull();

        layout.add(createHighlightSection());

        layout.add(createServiceHealth(), createResponseTimes());

        return layout;
    }

    private Component createFooter() {
        HorizontalLayout footer = new HorizontalLayout();
        footer.setWidthFull();
        footer.setPadding(true);
        footer.getStyle().set("background-color", "#333");
        footer.getStyle().set("color", "white");
        footer.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        footer.add(new Span("© 2024 Your Company. All rights reserved."));
        return new Footer(footer);
    }

    private Component createHighlightSection() {
        HorizontalLayout highlights = new HorizontalLayout();
        highlights.setWidthFull();
        highlights.setPadding(true);
        highlights.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        highlights.add(createHighlight("Current users", "745", 33.7),
                createHighlight("View events", "54.6k", -112.45),
                createHighlight("Conversion rate", "18%", 3.9),
                createHighlight("Custom metric", "-123.45", 0.0));

        return highlights;
    }

    private Component createHighlight(String title, String value, Double percentage) {
        VaadinIcon icon = VaadinIcon.ARROW_UP;
        String prefix = "";
        String badgeTheme = "badge";

        if (percentage == 0) {
            prefix = "±";
        } else if (percentage > 0) {
            prefix = "+";
            badgeTheme += " success";
        } else if (percentage < 0) {
            icon = VaadinIcon.ARROW_DOWN;
            badgeTheme += " error";
        }

        H2 header = new H2(title);
        Span valueSpan = new Span(value);
        valueSpan.addClassName("highlight-value");

        Icon trendIcon = icon.create();
        Span badge = new Span(trendIcon, new Span(prefix + percentage));
        badge.getElement().getThemeList().add(badgeTheme);

        VerticalLayout highlight = new VerticalLayout(header, valueSpan, badge);
        highlight.setSpacing(false);
        highlight.setPadding(false);

        return highlight;
    }

    private Component createServiceHealth() {
        H2 header = new H2("Service health");

        Grid<ServiceHealth> grid = new Grid<>(ServiceHealth.class);
        grid.setColumns("status", "city", "input", "output");
        grid.getColumnByKey("status").setHeader("Status");
        grid.getColumnByKey("city").setHeader("City");
        grid.getColumnByKey("input").setHeader("Input");
        grid.getColumnByKey("output").setHeader("Output");

        grid.setItems(getServiceHealthData());

        VerticalLayout layout = new VerticalLayout(header, grid);
        layout.setSpacing(true);
        return layout;
    }

    private Component createResponseTimes() {
        H2 header = new H2("Response times");

        Grid<ResponseTime> grid = new Grid<>(ResponseTime.class);
        grid.setColumns("system", "time");
        grid.getColumnByKey("system").setHeader("System");
        grid.getColumnByKey("time").setHeader("Response Time (ms)");

        grid.setItems(getResponseTimeData());

        VerticalLayout layout = new VerticalLayout(header, grid);
        layout.setSpacing(true);
        return layout;
    }

    private List<ServiceHealth> getServiceHealthData() {
        return Arrays.asList(
                new ServiceHealth(ServiceHealth.Status.EXCELLENT, "Berlin", 324, 1540),
                new ServiceHealth(ServiceHealth.Status.OK, "London", 311, 1320),
                new ServiceHealth(ServiceHealth.Status.FAILING, "New York", 300, 1219)
        );
    }

    private List<ResponseTime> getResponseTimeData() {
        return Arrays.asList(
                new ResponseTime("System 1", 120),
                new ResponseTime("System 2", 135),
                new ResponseTime("System 3", 150)
        );
    }

    public static class ServiceHealth {
        public enum Status {
            EXCELLENT, OK, FAILING
        }

        private final Status status;
        private final String city;
        private final int input;
        private final int output;

        public ServiceHealth(Status status, String city, int input, int output) {
            this.status = status;
            this.city = city;
            this.input = input;
            this.output = output;
        }

        public Status getStatus() {
            return status;
        }

        public String getCity() {
            return city;
        }

        public int getInput() {
            return input;
        }

        public int getOutput() {
            return output;
        }
    }

    public static class ResponseTime {
        private final String system;
        private final int time;

        public ResponseTime(String system, int time) {
            this.system = system;
            this.time = time;
        }

        public String getSystem() {
            return system;
        }

        public int getTime() {
            return time;
        }
    }
}
