package net.justmili.leftforgotten;

import net.justmili.leftforgotten.init.AlphaBlocks;
import net.minecraft.resources.ResourceLocation;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class LeftForgotten {
    public static final String MOD_ID = "left_forgotten";

    public static void init() {
        AlphaBlocks.register();
    }

    private static final ConcurrentLinkedQueue<WorkItem> workQueue = new ConcurrentLinkedQueue<>();

    private static class WorkItem {
        final Runnable task;
        int ticksRemaining;

        WorkItem(Runnable task, int delay) {
            this.task = task;
            this.ticksRemaining = delay;
        }
    }

    public static void queueServerWork(int tickDelay, Runnable action) {
        workQueue.add(new WorkItem(action, tickDelay));
    }

    public static void processQueue() {
        for (Iterator<WorkItem> iterator = workQueue.iterator(); iterator.hasNext(); ) {
            WorkItem item = iterator.next();
            if (item.ticksRemaining <= 0) {
                item.task.run();
                iterator.remove(); // safe to remove in ConcurrentLinkedQueue
            }
        }
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
