# task 1.2.5 from https://stepik.org/course/1547/syllabus
from typing import List, Optional


class StackWithMaximum:
    def __init__(self) -> None:
        self.storage = []
        self.__max_values = []

    def push(self, value: int) -> None:
        self.storage.append(value)

        if len(self.__max_values) == 0:
            maximum_in_stack = value
        else:
            maximum_in_stack = self.__max_values[-1]

        self.__max_values.append(max(value, maximum_in_stack))

    def pop(self) -> int:
        if len(self.storage) == 0:
            raise Exception('Could not pop element since there are 0 elements in stack!')

        self.__max_values.pop()
        return self.storage.pop()

    def max(self) -> Optional[int]:
        return 0 if len(self.__max_values) == 0 else self.__max_values[-1]


def get_maximums(numbers: List[int], window: int) -> List[int]:
    if len(numbers) == window:
        return [max(numbers)]

    if window == 1:
        return numbers

    if len(numbers) < window:
        raise Exception('Window is greater then len of numbers!')

    maximums = []
    left_stack = StackWithMaximum()
    right_stack = StackWithMaximum()

    ind = window

    for number in numbers[0:ind]:
        left_stack.push(number)

    while ind <= len(numbers):

        if len(right_stack.storage) == 0:
            for _ in range(window):
                right_stack.push(left_stack.pop())

        maximums.append(max(left_stack.max(), right_stack.max()))

        if ind == len(numbers):
            break

        # print('right_stack', right_stack.storage)
        # print('left_stack', left_stack.storage)

        right_stack.pop()
        left_stack.push(numbers[ind])

        ind += 1

    return maximums


def main() -> None:
    n = int(input())
    numbers = [int(number) for number in input().split(' ')]
    window = int(input())

    max_in_window = get_maximums(numbers, window)
    print(' '.join(map(str, max_in_window)))


if __name__ == '__main__':
    # assert get_maximums([2, 7, 3, 1, 5, 2, 6, 2], 4) == [7, 7, 5, 6, 6]
    # assert get_maximums([73, 65, 24, 14, 44, 20, 65, 97, 27, 6, 42, 1, 6, 41, 16], 7) ==\
    #        [73, 97, 97, 97, 97, 97, 97, 97, 42]
    # assert get_maximums([28, 7, 64, 40, 68, 86, 80, 93, 4, 53, 32, 56, 68, 18, 59], 12) == [93, 93, 93, 93]
    # assert get_maximums([2, 7, 3, 1, 5, 2, 6, 2], 1) == [2, 7, 3, 1, 5, 2, 6, 2]
    # assert get_maximums([2, 7, 3, 1, 5, 2, 6, 2], 8) == [7]

    main()
