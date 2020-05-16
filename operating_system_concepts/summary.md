
# 7

The readers - wrtiers problem has several variations, all involving priorities. The simplest one, referred toas the first readers - writers problem, requires that no reader be kept waiting unless a writer has already obtained permission to use the shared object. In other words, no reader should wait for other eaders to finish simply because a writer is waiting. The second readers - writers problem requires that, once a writer is ready, that writer perform its write as soon as possible. In other words, if a writer is waiting to access the object, no new readers may start reading.
 A solution to either problem may result in starvagion. In the first
case, writers may starve; in the second case, readers may starve.
For this reason, other variants of the problem have been proposed. Next, we present a solution to the first readers- writers problem. See the bibliographical notes at the end of the chapter for references describing starvation-free solutions to the second readers-writers problem.
In the solution to the first readers - writers problem, the reader processes share the following data structures:

 The binary semaphores mutex and rw_mutex are initialized to 1;
read_count is a couting semaphore initialized to 0. The semaphore rw_mutex is common to both reader and writer processes. The mutex semaphore is used to ensure mutual exclusion when the variable read_count is updated. The read_count variable keeps track of how many processes are currently reading the object. The semaphore rw_mutex functions as a mutual exclusion semaphore for the writers. It is also used by the first or last reader that enters or exits the critical section. It is not used by readers that enter or exit while other readers are in their critical sections.
  The code for a writer process is shown in Figure 7.3; the code for a reader process is shown in Figure 7.4. Note that, 