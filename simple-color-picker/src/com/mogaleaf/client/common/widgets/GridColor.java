package com.mogaleaf.client.common.widgets;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;

public class GridColor extends Composite implements ClickHandler
{
    private String             selectedColor = "";
    private List<ColorHandler> listners      = new ArrayList<ColorHandler>();

    String[][]                 colors        = new String[][]{
                                             // black to white
        {"#000000", "#444444", "#888888", "#bbbbbb", "#ffffff"},
        // blue
        {"#002851", "#006bd6", "#3399FF", "#70b7ff", "#b7dbff"},
        // red
        {"#5b0000", "#c10000", "#FF0000", "#ff3d3d", "#ff8e8e"},
        // orange
        {"#c17500", "#ea8d00", "#ffa214", "#ffba51", "#ffd28e"},
        // yellow
        {"#7a7a00", "#d6d600", "#FFFF00", "#feff70", "#fefeb7"},
        // green
        {"#004700", "#00b700", "#00FF00", "#47ff47", "#a3fea3"},
        // purple
        {"#73165b", "#b72391", "#d62dab", "#e169c3", "#efadde"}

                                             };

    public interface MyResources extends ClientBundle
    {
        public static final MyResources INSTANCE = GWT.create(MyResources.class);

        @Source("Grid.css")
        public MyCssResource css();

    }

    interface MyCssResource extends CssResource
    {
        String grid();
    }

    public GridColor()
    {
        MyResources.INSTANCE.css().ensureInjected();
        Grid grid = new Grid(colors.length, colors[0].length);
        grid.setCellSpacing(0);
        grid.setCellPadding(0);
        grid.setStyleName(MyResources.INSTANCE.css().grid());
        for (int i = 0; i < colors.length; i++)
        {

            for (int j = 0; j < colors[0].length; j++)
            {
                String color = colors[i][j];
                grid.setWidget(i, j, new ColorLabel(color, this));

            }
        }
        initWidget(grid);
    }

    @Override
    public void onClick(ClickEvent event)
    {

        if (event.getSource() instanceof ColorLabel)
        {
            ColorLabel label = (ColorLabel) event.getSource();
            synchronized (listners)
            {
                for (ColorHandler handler : listners)
                {
                    handler.newColorSelected(label.getColor());
                }
            }
        }
    }

    public void addListner(ColorHandler handler)
    {
        synchronized (listners)
        {
            listners.add(handler);
        }

    }

    public void removeListner(ColorHandler handler)
    {
        synchronized (listners)
        {
            listners.remove(handler);
        }
    }

    public String getSelectedColor()
    {
        return selectedColor;
    }
}
