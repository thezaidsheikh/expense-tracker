from utils.MessageUtil import MessageUtil
from service.LLMService import LLMService
class MessageService:
    def __init__(self):
        self.message_util = MessageUtil()
        self.llm_service = LLMService()
    
    def handle_message(self, message):
        if self.message_util.isBankSms(message):
            return self.llm_service.runLLM(message)
        return None
