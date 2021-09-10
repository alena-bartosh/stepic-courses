# task 2.2.1 from https://stepik.org/course/1547/syllabus
from typing import List, Tuple


class MinHeap:
    def __init__(self, priorities: List[int]) -> None:
        self.__heap = priorities
        self.size = len(self.__heap)
        self.__swaps: List[Tuple[int, int]] = []

    def get_parent_ind(self, node_ind: int) -> int:
        return (node_ind - 1) // 2

    def get_left_child_ind(self, node_ind: int) -> int:
        return 2 * node_ind + 1

    def get_right_child_ind(self, node_ind: int) -> int:
        return 2 * node_ind + 2

    def is_leaf(self, node_ind: int) -> bool:
        if (self.size // 2) <= node_ind <= self.size:
            return True

        return False

    def swap(self, left_node_ind: int, right_node_ind: int) -> None:
        self.__heap[left_node_ind], self.__heap[right_node_ind] = \
            self.__heap[right_node_ind], self.__heap[left_node_ind]

        self.__swaps.append((left_node_ind, right_node_ind))

    def sift_down(self, node_ind: int, size: int) -> None:
        # print(f'self.__heap = {self.__heap}')
        # print(f'Sift [node_ind={node_ind}]...')

        min_node_ind = node_ind

        left_child_ind = self.get_left_child_ind(node_ind)
        right_child_ind = self.get_right_child_ind(node_ind)

        if left_child_ind < size and self.__heap[left_child_ind] < self.__heap[min_node_ind]:
            min_node_ind = left_child_ind

        if right_child_ind < size and self.__heap[right_child_ind] < self.__heap[min_node_ind]:
            min_node_ind = right_child_ind

        if node_ind != min_node_ind:
            self.swap(node_ind, min_node_ind)
            self.sift_down(min_node_ind, size)

    def build(self) -> None:
        for ind in range(((self.size // 2) - 1), -1, -1):
            self.sift_down(ind, size=self.size)

    def print_swaps(self) -> None:
        print(len(self.__swaps))
        for from_ind, to_ind in self.__swaps:
            print(from_ind, to_ind)

    def sort(self) -> None:
        s = self.size

        for i in range(self.size - 1):
            self.swap(0, s - 1)
            s -= 1
            self.sift_down(0, size=s)

    @property
    def priorities(self) -> List[int]:
        return self.__heap


def main() -> None:
    n = int(input())
    priorities = list(map(int, input().split(' ')))
    heap = MinHeap(priorities)
    heap.build()
    heap.print_swaps()


if __name__ == '__main__':
    main()
