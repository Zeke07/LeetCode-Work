class validParen:
    '''
    Uses a stack to check if each "{,(,[" is closed in the same order that it appears starting from right->left
    '''
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
