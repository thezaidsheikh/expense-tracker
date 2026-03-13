import re

class MessageUtil:
    
    def isBankSms(self, message):
        word_to_search = ["bank card","credit card", "transaction", "amount", "Txn", "debited"]
        regex = r'\b(' + '|'.join(word_to_search) + r')\b'
        return bool(re.search(regex, message, re.IGNORECASE))
        
