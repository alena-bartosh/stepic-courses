# task 2.3.3 from https://stepik.org/course/1547/syllabus
from typing import List


class DisjointSets:
    def __init__(self, sizes: List[int]) -> None:

        self.parent: List[int] = [_ for _ in range(len(sizes) + 1)]
        self.value = [0] + sizes
        self.max = max(self.value)

    def find_set_id(self, el: int) -> int:
        # NOTE: Here compression of paths is used for optimization

        if el != self.parent[el]:
            self.parent[el] = self.find_set_id(self.parent[el])

        return self.parent[el]

    def union(self, dest: int, source: int) -> None:
        if dest == source:
            return

        dest_set_id = self.find_set_id(dest)
        source_set_id = self.find_set_id(source)

        if dest_set_id == source_set_id:
            return

        # print(f'[values={self.value}]')

        self.parent[source_set_id] = dest_set_id
        self.value[dest_set_id] += self.value[source_set_id]
        self.value[source_set_id] = 0
        self.max = max(self.max, self.value[dest_set_id])

    def print_max(self) -> None:
        print(self.max)


def main() -> None:
    tables_count, queries_count = list(map(int, input().split(' ')))
    table_sizes = list(map(int, input().split(' ')))

    disjoint_sets = DisjointSets(table_sizes)

    for _ in range(queries_count):
        dest, source = list(map(int, input().split(' ')))
        disjoint_sets.union(dest, source)
        disjoint_sets.print_max()


if __name__ == '__main__':
    main()
