package com.mogaleaf.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.mogaleaf.client.common.widgets.ColorHandler;
import com.mogaleaf.client.common.widgets.SimpleColorPicker;

public class EntryPoint implements com.google.gwt.core.client.EntryPoint
{
    final SimpleColorPicker picker = new SimpleColorPicker();
    final Button            button = new Button();

    @Override
    public void onModuleLoad()
    {
        picker.addListner(new ColorHandler()
        {

            @Override
            public void newColorSelected(String color)
            {
                // What you want to do with the color here
                Window.alert("you choose " + color);
            }
        });
        button.addClickHandler(new ClickHandler()
        {

            @Override
            public void onClick(ClickEvent event)
            {
                setPopupPositionAndShow(picker, button);
            }
        });
        RootPanel.get().add(new SimplePanel(button));
    }

    private void setPopupPositionAndShow(final PopupPanel popup, final Widget button)
    {
        popup.setPopupPositionAndShow(new PopupPanel.PositionCallback()
        {
            @Override
            public void setPosition(int offsetWidth, int offsetHeight)
            {
                int left = button.getAbsoluteLeft() - offsetWidth + button.getOffsetWidth();
                int top = button.getAbsoluteTop() + button.getOffsetHeight();
                popup.setPopupPosition(left, top);
            }
        });
    }
}
