package software.bernie.techarium.datagen;

import net.minecraft.data.DataGenerator;
import software.bernie.techarium.registry.BlockRegistry;
import software.bernie.techarium.registry.ItemGroupRegistry;
import software.bernie.techarium.registry.ItemRegistry;
import software.bernie.techarium.registry.LangRegistry;

public class TechariumLangProvider extends TechariumLangProviderBase {
    public TechariumLangProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void addTranslations() {
        addBlocks();
        addItems();
        addTranslationComponents();
        addItemGroups();
    }

    private void addItemGroups() {
        add(ItemGroupRegistry.TECHARIUM, "Techarium");
    }

    private void addItems() {
        addItem(ItemRegistry.DEBUGSTICK, "Pipe Stick");
        addItem(ItemRegistry.ITEM_PIPE, "Item Pipe");
        addItem(ItemRegistry.FLUID_PIPE, "Fluid Pipe");
        addItem(ItemRegistry.ENERGY_PIPE, "Energy Pipe");
        addItem(ItemRegistry.ALUMINIUM_INGOT, "Aluminium Ingot");
        addItem(ItemRegistry.COPPER_INGOT, "Copper Ingot");
        addItem(ItemRegistry.LEAD_INGOT, "Lead Ingot");
    }

    protected void addBlocks() {
        addBlock(BlockRegistry.BOTARIUM, "Botarium");
        addBlock(BlockRegistry.BOTARIUM_TOP, "Botarium");
        addBlock(BlockRegistry.ARBORETUM, "Arboretum");
        addBlock(BlockRegistry.ARBORETUM_TOP, "Arboretum");
        addBlock(BlockRegistry.ALUMINIUM_ORE, "Aluminium Ore");
        addBlock(BlockRegistry.COPPER_ORE, "Copper Ore");
        addBlock(BlockRegistry.LEAD_ORE, "Lead Ore");
    }

    private void addTranslationComponents() {
        addTranslation(LangRegistry.topProgressETA, "ETA: ");
        addTranslation(LangRegistry.hwylaProgressETA, "ETA: %s seconds remaining");
        addTranslation(LangRegistry.hwylaProgressNoRecipe, "ETA: No valid recipe");
        addTranslation(LangRegistry.jeiBotariumDescription, "The Botarium allows you to grow crops in exchange for energy and water");
        addTranslation(LangRegistry.jeiArboretumDescription, "The Arboretum allows you to grow saplings in exchange for energy and water");
    }
}
