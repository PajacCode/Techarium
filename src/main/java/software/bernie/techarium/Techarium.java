package software.bernie.techarium;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.techarium.datagen.*;
import software.bernie.techarium.integration.ModIntegrations;
import software.bernie.techarium.integration.theoneprobe.TheOneProbeIntegration;
import software.bernie.techarium.machine.screen.AutomaticContainerScreen;
import software.bernie.techarium.pipes.NetworkEvents;
import software.bernie.techarium.network.NetworkConnection;
import software.bernie.techarium.registry.*;
import software.bernie.techarium.world.WorldGen;

import static software.bernie.techarium.registry.ContainerRegistry.AUTO_CONTAINER;

@Mod(Techarium.ModID)
public class Techarium
{
	public final static String ModID = "techarium";
	public static Logger LOGGER;

	public Techarium()
	{
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        GeckoLib.initialize();
        LOGGER = LogManager.getLogger();
		ItemRegistry.register(bus);
		BlockRegistry.register(bus);
		ContainerRegistry.register(bus);
		RecipeRegistry.register(bus);
		bus.addListener(this::onClientSetup);
		bus.addListener(NetworkEvents::onCommonSetup);
		bus.addListener(this::gatherData);
		bus.addListener(this::enqueueIMC);
		MinecraftForge.EVENT_BUS.addListener(WorldGen::generateOres);

		NetworkConnection.registerMessages();
	}

	public static ResourceLocation rl(String path){
		return new ResourceLocation(Techarium.ModID, path);
	}

	private void gatherData(GatherDataEvent event)
	{
		DataGenerator generator = event.getGenerator();
		generator.addProvider(new TechariumRecipeProvider(generator));
		generator.addProvider(new TechariumLangProvider(generator));
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		TechariumBlockTagsProvider provider = new TechariumBlockTagsProvider(generator, existingFileHelper);
		generator.addProvider(provider);
		generator.addProvider(new TechariumItemTagsProvider(generator, provider, existingFileHelper));
		generator.addProvider(new TechariumLootTables(generator));
	}

	public void enqueueIMC(InterModEnqueueEvent event) {
		ModIntegrations.getTheOneProbe().ifPresent(TheOneProbeIntegration::requestTheOneProbe);
	}

	public void onClientSetup(FMLClientSetupEvent event)
	{
		ScreenManager.register(AUTO_CONTAINER.get(), AutomaticContainerScreen::new);
	}
}
