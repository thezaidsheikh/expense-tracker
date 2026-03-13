from typing import Optional
from pydantic import BaseModel, Field

class ExpenseSchema(BaseModel):
    amount: Optional[str] = Field(title="expense", description="The expense made in the transaction")
    merchant: Optional[str] = Field(title="merchant", description="Merchant name to whom the expense was made")
    currency: Optional[str] = Field(title="currency", description="Currency of the transaction like USD, EUR, etc.")
    bank: Optional[str] = Field(title="bank", description="Bank name")
    transaction_type: Optional[str] = Field(title="transaction_type", description="Transaction type like debit, credit")
    account_number_masked: Optional[str] = Field(title="account_number_masked", description="Account number last 4 digits")
    transaction_date: Optional[str] = Field(title="transaction_date", description="Transaction date")
    transaction_time: Optional[str] = Field(title="transaction_time", description="Transaction time")
    payment_method: Optional[str] = Field(title="payment_method", description="Payment method like UPI, Card, etc.")
    transaction_category: Optional[str] = Field(title="transaction_category", description="Transaction category like P2M, P2P, etc.")
    transaction_id: Optional[str] = Field(title="transaction_id", description="Transaction ID")
    