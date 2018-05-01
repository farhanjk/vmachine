package com.vending.machine.app.common.util;

import com.vending.machine.app.common.concurrency.threadqueue.UiThreadQueue;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doAnswer;

public class MockUiThreadQueue {

    public static UiThreadQueue newInstance() {
        UiThreadQueue threadQueue = Mockito.mock(UiThreadQueue.class);

        AtomicBoolean isEnabled = new AtomicBoolean(false);

        doAnswer(invocation -> {
            if (!isEnabled.get()) {
                return null;
            }
            Object[] args = invocation.getArguments();
            ((Runnable) args[0]).run();
            return null;
        }).when(threadQueue).run(any());

        doAnswer(invocation -> {
            if (!isEnabled.get()) {
                return null;
            }
            Object[] args = invocation.getArguments();
            ((Runnable) args[0]).run();
            return null;
        }).when(threadQueue).runDelayed(any(), anyLong());

        doAnswer((InvocationOnMock invocation) -> {
            isEnabled.set(invocation.getArgument(0));
            return null;
        }).when(threadQueue).setEnabled(anyBoolean());

        return threadQueue;
    }
}
