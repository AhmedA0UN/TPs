def solve_game(test_cases):
    results = []
    for x in test_cases:
        k = len(str(x))
        y = 10**k - x
        results.append(y)
    return results


t = int(input())
test_cases = [int(input()) for _ in range(t)]
answers = solve_game(test_cases)
for y in answers:
    print(y)
