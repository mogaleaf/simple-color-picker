package com.mogaleaf.client.common.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * Created By Mogaleaf, see <a href="http://www.mogaleaf.com">http://www.mogaleaf.com</a>
 * @author Cecile GOMES
 *         SimpleColorPicker show a popup with a table of 35 colors.
 *         A handler call you back when a color is selected.
 */
public class SimpleColorPicker extends PopupPanel implements ClickHandler
{
    private GridColor picker;

    public interface MyResources extends ClientBundle
    {
        public static final MyResources INSTANCE = GWT.create(MyResources.class);

        @Source("Popup.css")
        public MyCssResource css();

    }

    interface MyCssResource extends CssResource
    {
        String popup();
    }

    public SimpleColorPicker()
    {
        super(true); // autohide
        MyResources.INSTANCE.css().ensureInjected();
        setTitle("Colors");
        picker = new GridColor();
        this.setModal(true);
        this.addDomHandler(this, ClickEvent.getType());
        setStyleName(MyResources.INSTANCE.css().popup());
        setWidget(picker);
    }

    @Override
    public void onClick(ClickEvent event)
    {
        SimpleColorPicker.this.hide();
    }

    public void addListner(ColorHandler handler)
    {
        picker.addListner(handler);
    }

    public void removeListner(ColorHandler handler)
    {
        picker.removeListner(handler);
    }
}
