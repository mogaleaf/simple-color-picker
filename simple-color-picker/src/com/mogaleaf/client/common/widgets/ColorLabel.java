package com.mogaleaf.client.common.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.HTML;

public class ColorLabel extends HTML
{
    
    public interface MyResources extends ClientBundle {
        public static final MyResources INSTANCE =  GWT.create(MyResources.class);

        @Source("ColorLabel.css")
        public MyCssResource css();

      }

    interface MyCssResource extends CssResource
    {
        String colorLabel();
    }
    
    private String color ;
    public ColorLabel(String color,ClickHandler handler)
    {
        this.color = color;
        setTitle(color);
        MyResources.INSTANCE.css().ensureInjected();
        setStyleName(MyResources.INSTANCE.css().colorLabel());
        this.getElement().getStyle().setBackgroundColor(color);
        this.addClickHandler(handler);
    }
    
    public String getColor()
    {
        return color;
    }
}
