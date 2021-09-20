# task 3.2.2 from https://stepik.org/course/1547/syllabus
from typing import List


MAX_STRING_LENGTH = 15
P = 1_000_000_007  # big prime number
X = 263  # some fixed base


class HashTableWithChaining:

    x_degree_mod_p = [1]
    for i in range(MAX_STRING_LENGTH):
        x = (x_degree_mod_p[i] * X) % P
        x_degree_mod_p.append(x)

    def __init__(self, size: int) -> None:
        self.size = size
        self.__slot: List[List[str]] = [[] for _ in range(size)]

    def _hash(self, string: str) -> int:
        # Polynomial hash function on strings
        h = 0
        for i in range(len(string)):
            h += ord(string[i]) * self.x_degree_mod_p[i]

        return h % P % self.size

    def add(self, string: str) -> None:
        string_hash = self._hash(string)

        if string in self.__slot[string_hash]:
            return

        self.__slot[string_hash].insert(0, string)

    def remove(self, string: str) -> None:
        string_hash = self._hash(string)

        if string not in self.__slot[string_hash]:
            return

        self.__slot[string_hash].remove(string)

    def contains(self, string: str) -> bool:
        string_hash = self._hash(string)

        return string in self.__slot[string_hash]

    def get_chain_by_index(self, ind: int) -> List[str]:
        return self.__slot[ind]


class CommandExecutor:
    def __init__(self, hash_table: HashTableWithChaining) -> None:
        self.hash_table = hash_table

    def execute(self, command: List[str]) -> None:
        action = command[0]
        value = command[1]

        if action == 'add':
            self.hash_table.add(value)

        if action == 'del':
            self.hash_table.remove(value)

        if action == 'find':
            print('yes' if self.hash_table.contains(value) else 'no')

        if action == 'check':
            value = int(value)
            print(' '.join(self.hash_table.get_chain_by_index(value)))


def main() -> None:
    m = int(input())
    commands_count = int(input())

    hash_table = HashTableWithChaining(m)
    executor = CommandExecutor(hash_table)

    for _ in range(commands_count):
        executor.execute(input().split(' '))


if __name__ == '__main__':
    main()
