def test(s):
    x = set(s)
    
    if len(x) != len(s):
        return False

    positions = [ord(c) for c in s]
    return max(positions) - min(positions) + 1 == len(s)

n = int(input())
for i in range(n):
    s = input().strip()
    print("Yes" if test(s) else "No")
