package org.zhakimel.vgrails.app

import com.vaadin.ui.Button


import com.vaadin.ui.Button.ClickEvent
import com.vaadin.ui.CssLayout
import org.zhakimel.vgrails.util.AppManager
import org.zhakimel.vgrails.util.VGrailsConstant

class NavigationPanel extends CssLayout implements Button.ClickListener {

  Button selectedButton;
  private VGrailsApplication app
  private AppManager manager

  private CssLayout hLayout
  private String buttonWidth=VGrailsConstant.DEFAULT_MAINMENU_ITEM_WIDTH

  /**
   * Constructor
   */
  NavigationPanel(AppManager manager){
    this.manager = manager
    this.app = manager.app
    build()
  }


  /**
   * build menu buttons
   */
  def build(){
    addStyleName "toolbar"
    setWidth "100%"
    setHeight "28px"
    hLayout = new CssLayout()
    hLayout.addStyleName("right");
    hLayout.setSizeUndefined();

    for (menu in manager.NAVIGATION_APP_PANEL) {
      Button b = new Button(menu.key,this)
      b.setWidth buttonWidth
      hLayout.addComponent  b
    }
    addComponent  hLayout
  }

  void buttonClick(ClickEvent clickEvent) {
     String caption = clickEvent.getButton().getCaption()
     app.subNavigationPanel.buildMenu caption

    if(clickEvent.button!=selectedButton){
      if(selectedButton) selectedButton.removeStyleName("default")
      selectedButton=clickEvent.button
      selectedButton.addStyleName "default"

    }

  }


}
