class validParen:
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        charList=[]
        for char in s:
            if (char=="(" or char=="{" or char=="["):
                charList.append(char)
                continue
            if (len(charList)==0):
                return False
            var=charList.pop()
            if (var=="(" and char!=")"):
                return False
            if (var=="{" and char!="}"):
                return False
            if (var=="[" and char!= "]"):
                return False
        if (len(charList)==0):
            return True
        return False
test = validParen()
print(test.isValid(s="()[]"))
