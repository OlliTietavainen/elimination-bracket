package com.vaadin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.model.Player;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.vaadin.MyAppWidgetset")
public class MyUI extends UI {
	
	private String[] names = {"foo", "bar", "baz", "qux", 
								"quux", "corge", "grault", "garply"};
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        TournamentDesignImpl tournament = new TournamentDesignImpl("Name of tournament", createDummyData());
        layout.addComponent(tournament);
        tournament.setSizeFull();
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    private List<Player> createDummyData() {
    	List<Player> players = new ArrayList<>();
    	int c = names.length;
    	for (int i = 0; i < c; i++) {
    		Player player = new Player(names[i]);
    		players.add(player);
    	}
    	Collections.shuffle(players, new Random(System.nanoTime()));
    	return players;
    }
}
