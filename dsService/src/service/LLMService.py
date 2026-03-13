from dotenv import load_dotenv
from langchain_core.prompts import ChatPromptTemplate, MessagesPlaceholder
from langchain_google_genai import ChatGoogleGenerativeAI
from schema.ExpenseSchema import ExpenseSchema;

import os

load_dotenv()

class LLMService:
    def __init__(self):
        self.prompt = ChatPromptTemplate.from_messages(
            [("system", "You are an expert extraction algorithm."
            "Your task is to only extract relevant information from the given message."
            "If you do not know the value of an attrribute asked to extract, return null for that attribute."),("human","{message}")]
        )
        self.api_key = os.getenv("GEMINI_API_KEY")
        self.llm = ChatGoogleGenerativeAI(model="gemini-2.5-flash", api_key=self.api_key)
        self.runable = self.prompt | self.llm.with_structured_output(schema=ExpenseSchema)
    
    def runLLM(self, message):
        return self.runable.invoke({"message": message})