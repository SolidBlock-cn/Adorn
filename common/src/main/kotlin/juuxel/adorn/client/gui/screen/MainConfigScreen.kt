package juuxel.adorn.client.gui.screen

import juuxel.adorn.config.ConfigManager
import juuxel.adorn.fluid.FluidUnit
import juuxel.adorn.item.group.ItemGroupingOption
import juuxel.adorn.util.property
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.Text

class MainConfigScreen(parent: Screen) : AbstractConfigScreen(Text.translatable("gui.adorn.config.title"), parent) {
    override fun init() {
        super.init()
        val config = ConfigManager.config()
        val x = (width - BUTTON_WIDTH) / 2
        addHeading(Text.translatable("gui.adorn.config.visual"), BUTTON_WIDTH)
        addConfigToggle(BUTTON_WIDTH, config.client.property { it::showTradingStationTooltips })
        addConfigButton(BUTTON_WIDTH, config.client.property { it::displayedFluidUnit }, FluidUnit.values().toList())
        addHeading(Text.translatable("gui.adorn.config.creative_inventory"), BUTTON_WIDTH)
        addConfigToggle(BUTTON_WIDTH, config.client.property { it::showItemsInStandardGroups })
        addConfigButton(
            BUTTON_WIDTH,
            config.property { it::groupItems },
            ItemGroupingOption.values().toList(),
            restartRequired = true
        )
        addHeading(Text.translatable("gui.adorn.config.other"), BUTTON_WIDTH)
        addDrawableChild(
            ButtonWidget.builder(Text.translatable("gui.adorn.config.game_rule_defaults")) {
                client!!.setScreen(GameRuleDefaultsScreen(this))
            }
                .position(x, nextChildY)
                .size(BUTTON_WIDTH, 20)
                .build()
        )
        addDrawableChild(
            ButtonWidget.builder(ScreenTexts.DONE) { close() }
                .position(this.width / 2 - 100, this.height - 27)
                .size(200, 20)
                .build()
        )
    }

    companion object {
        private const val BUTTON_WIDTH = 200
    }
}
