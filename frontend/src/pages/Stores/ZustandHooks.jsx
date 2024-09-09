import { create } from "zustand";

export const useCounterStore = create((set) => ({
    count: 0,
    increment: () => set((state) => ({ count: state.count + 1 })),
    decrement: () => set((state) => ({ count: state.count - 1 })),
    reset: () => set({ count: 0 }),
}));

export const useColorStore = create((set) => ({
    color: "빨강",
    changeColor: () => set((state) => ({ color: state.color === "빨강" ? "파랑" : "빨강" })),
}));