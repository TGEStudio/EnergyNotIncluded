package net.tgestudio.energynotincluded.registry_file;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tgestudio.energynotincluded.EnergyNotIncluded;
import net.tgestudio.energynotincluded.block.construction.ConsSlave;
import net.tgestudio.energynotincluded.block.construction.ConsSlaveEntity;
import net.tgestudio.energynotincluded.block.construction.ConstructorBlock;
import net.tgestudio.energynotincluded.block.construction.ConstructorBlockEntity;
import net.tgestudio.energynotincluded.block.custom_multiblock.CoalGenerator;
import net.tgestudio.energynotincluded.block.custom_multiblock.CoalGeneratorEntity;

import java.util.function.Supplier;

import static net.tgestudio.energynotincluded.registry_file.RegsItems.ITEMS;

public class RegsBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EnergyNotIncluded.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EnergyNotIncluded.MOD_ID);

    /*public static final RegistryObject<Block> ALGAE_BLOCK = registryBlock("algae_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT).sound(SoundType.NETHER_WART)));*/
    public static final RegistryObject<Block> CONSBLOCK = registryBlock("constructor_block",
            () -> new ConstructorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<BlockEntityType<ConstructorBlockEntity>> CONSBLOCK_ENTITY = registryBlockEntity("constructor_block_entity",
            () -> BlockEntityType.Builder.of(ConstructorBlockEntity::new, CONSBLOCK.get()).build(null));

    public static final RegistryObject<Block> COAL_GENERATOR = registryBlock("coal_generator",
            () -> new CoalGenerator(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable()));
    public static final RegistryObject<BlockEntityType<CoalGeneratorEntity>> COAL_GENERATOR_ENTITY = registryBlockEntity("coal_generator_entity",
            () -> BlockEntityType.Builder.of(CoalGeneratorEntity::new, COAL_GENERATOR.get()).build(null));

    public static final RegistryObject<Block> SLAVE = registryBlock("slave",
            () -> new ConsSlave(BlockBehaviour.Properties.copy(Blocks.STONE).noLootTable().noOcclusion()));
    public static final RegistryObject<BlockEntityType<ConsSlaveEntity>> SLAVE_ENTITY = registryBlockEntity("slave_entity",
            () -> BlockEntityType.Builder.of(ConsSlaveEntity::new, SLAVE.get()).build(null));
    public static final RegistryObject<Block> MASTER = registryBlock("master",
            () -> new ConsSlave(BlockBehaviour.Properties.copy(Blocks.STONE).noLootTable()));
    public static final RegistryObject<BlockEntityType<ConsSlaveEntity>> MASTER_ENTITY = registryBlockEntity("master_entity",
            () -> BlockEntityType.Builder.of(ConsSlaveEntity::new, MASTER.get()).build(null));

    public static final RegistryObject<Block> PLACEABLE_BLOCK = registryBlock("placeable_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .noLootTable().noOcclusion()
            ));
    public static final RegistryObject<Block> PLACEABLE_BLOCK_WRONG = registryBlock("placeable_block_wrong",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .noLootTable().noOcclusion()
            ));

    //Test  multiblock


    /*public static final RegistryObject<Block> SLAVE = registryBlock("slave",
            () -> new MultiBlockTest(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<BlockEntityType<MultiBlockTestEntity>> SLAVE_ENTITY = registryBlockEntity("slave",
            () -> BlockEntityType.Builder.of(MultiBlockTestEntity::new, SLAVE.get()).build(null));*/


    public static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registryBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registryBlockItem(String name, RegistryObject<T> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static <T extends BlockEntityType<?>> RegistryObject<T> registryBlockEntity(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCK_ENTITIES.register(name, block);
        return toReturn;
    }

    public final BlockState defaultBlockState() {
        StateDefinition.Builder<Block, BlockState> builder = new StateDefinition.Builder(this);
        return (BlockState) builder.create(Block::defaultBlockState, BlockState::new).any();
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
    }

}
