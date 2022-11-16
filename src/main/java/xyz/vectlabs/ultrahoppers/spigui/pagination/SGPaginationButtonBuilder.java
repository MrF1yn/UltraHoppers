package xyz.vectlabs.ultrahoppers.spigui.pagination;


import xyz.vectlabs.ultrahoppers.spigui.SGMenu;
import xyz.vectlabs.ultrahoppers.spigui.buttons.SGButton;

public interface SGPaginationButtonBuilder {

    SGButton buildPaginationButton(SGPaginationButtonType type, SGMenu inventory);

}
