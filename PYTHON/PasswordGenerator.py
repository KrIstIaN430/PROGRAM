import random
import string
number_list = range(10)
char_lower_list = string.ascii_lowercase
char_upper_list = string.ascii_uppercase
special_char_list = ["@", "!", "+", "-", "?", "#", "&"]

pass_list = [number_list, char_lower_list, char_upper_list, special_char_list]

#METHOD 1
#for i in range(13): #13
#    print(random.choice(random.choice(pass_list)), end='')

s = "abcdefghijklmnopqrstuvwxyz01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()?"

#METHOD 2
passlen = 8
p = "".join(random.sample(s, passlen))
# print("\n" + p)

#METHOD 3
def pw_gen(size, chars=string.ascii_letters + string.digits + string.punctuation):
    return ''.join(random.choice(chars) for _ in range(size))


print(pw_gen(int(input('How many characters in your password? '))))
