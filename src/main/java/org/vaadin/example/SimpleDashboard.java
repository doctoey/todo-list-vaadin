package org.vaadin.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.List;

@PageTitle("Simple Dashboard")
@Route("simple-dashboard")
public class SimpleDashboard extends Div {

    public SimpleDashboard() {
        addClassName("dashboard-view");
        setSizeFull();

        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();

        mainLayout.add(createSidebar(), createMainContent());

        add(mainLayout);
    }

    private Component createSidebar() {
        VerticalLayout sidebar = new VerticalLayout();
        sidebar.setWidth("200px");
        sidebar.getStyle().set("background-color", "#f8f9fa");

        Button dashboardButton = new Button("Dashboard");
        Button settingsButton = new Button("Settings");

        sidebar.add(dashboardButton, settingsButton);
        return sidebar;
    }

    private Component createMainContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        H2 title = new H2("Dashboard Overview");

        Grid<Item> grid = new Grid<>(Item.class);
        grid.setItems(getData());

        layout.add(title, grid);
        return layout;
    }

    private List<Item> getData() {
        return Arrays.asList(
                new Item("Item 1", 100),
                new Item("Item 2", 200),
                new Item("Item 3", 300)
        );
    }

    public static class Item {
        private String name;
        private int value;

        public Item(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }
    }
}
