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
import com.vaadin.server.Page;
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

    final VerticalLayout layout = new VerticalLayout();
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        layout.setSizeFull();
        Window window = new Window();
        InitDialogDesignImpl initDialog = new InitDialogDesignImpl(window, this);
        window.setWidth(400, Unit.PIXELS);
        window.setHeight(600, Unit.PIXELS);
        window.setModal(true);
        window.setClosable(false);
        window.setContent(initDialog);
        window.center();
        getUI().addWindow(window);
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
    
    protected void startTournament(List<Player> players, String tournamentName) {
    	Collections.shuffle(players, new Random(System.nanoTime()));
    	TournamentDesignImpl tournament = new TournamentDesignImpl(tournamentName, players);
        layout.addComponent(tournament);
        tournament.setSizeFull();
    }
    
}
