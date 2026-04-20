import React, { useState } from 'react';
import './LycaReacargeComponent.css';

const LycaReachargeComponent = (props) => {

  const [phone, setPhone] = useState('');
  const [activeTab, setActiveTab] = useState(props.planTabs?.[0]?.tabKey);
  const [selectedPlan, setSelectedPlan] = useState(null);   // { price, tabKey, data }
  const [selectedTopUp, setSelectedTopUp] = useState(null); // { amount }

  // Phone is valid when exactly 10 digits
  const isPhoneValid = phone.replace(/\D/g, '').length === 10;

  const handlePhoneChange = (e) => {
    const val = e.target.value.replace(/\D/g, '').slice(0, 10);
    setPhone(val);
    // Reset selections if phone becomes invalid
    if (val.length < 10) {
      setSelectedPlan(null);
      setSelectedTopUp(null);
    }
  };

  const filteredPlans = props.plans?.filter(
    (plan) => plan.tabKey === activeTab
  );

  // Plan section amount — only from selected plan
  const planAmount = selectedPlan
    ? parseFloat(selectedPlan.price.replace('$', '')) || 0
    : 0;

  // TopUp section amount — only from selected top-up
  const topUpAmount = selectedTopUp
    ? parseFloat(selectedTopUp.amount.replace('$', '')) || 0
    : 0;

  // Total = whichever section has a selection (they are mutually independent per point 4)
  // Grand total = planAmount + topUpAmount (user may select both)
  const selectedAmount = planAmount + topUpAmount;
  const fee = (selectedAmount * (props.regulatoryFeePercentage || 0)) / 100;
  const total = selectedAmount + fee;

  const handlePlanSelect = (plan) => {
    if (!isPhoneValid) return;
    // Toggle off if same plan clicked again
    if (selectedPlan?.data === plan.data && selectedPlan?.tabKey === plan.tabKey) {
      setSelectedPlan(null);
    } else {
      setSelectedPlan(plan);
    }
  };

  const handleTopUpSelect = (item) => {
    if (!isPhoneValid) return;
    // Toggle off if same top-up clicked again
    if (selectedTopUp?.amount === item.amount) {
      setSelectedTopUp(null);
    } else {
      setSelectedTopUp(item);
    }
  };

  const handleConfirm = () => {
    if (!isPhoneValid) {
      alert('Please enter a valid 10-digit mobile number.');
      return;
    }
    if (!selectedPlan && !selectedTopUp) {
      alert('Please select a plan or top-up amount.');
      return;
    }
    alert('Recharge Successful! 🎉');
  };

  // Disabled overlay style for inactive sections
  const disabledStyle = {
    opacity: 0.45,
    pointerEvents: 'none',
    userSelect: 'none',
  };

  return (
    <div className="lyca-recharge-wrapper">
      <div className="lyca-recharge-container">

        {/* Header */}
        <h2 className="lyca-recharge-title">{props.title}</h2>
        <div
          className="lyca-recharge-description"
          dangerouslySetInnerHTML={{ __html: props.description }}
        />

        {/* Phone Section — always active */}
        <div className="recharge-card">
          <label>{props.operatorTitle}</label>
          <div className="phone-input">
            <span>{props.countryCode}</span>
            <input
              type="tel"
              maxLength={10}
              value={phone}
              onChange={handlePhoneChange}
              placeholder={props.phoneInputPlaceholder}
            />
          </div>
        </div>

        {/* Plans Section */}
        <div className="recharge-card" style={!isPhoneValid ? disabledStyle : {}}>
          <div className="section-header">
            <h3>{props.plansSectionTitle}</h3>
            <span>${planAmount.toFixed(2)}</span>
          </div>

          {/* Tabs */}
          <div className="tabs">
            {props.planTabs?.map((tab, index) => (
              <button
                key={index}
                className={activeTab === tab.tabKey ? 'active' : ''}
                onClick={() => {
                  setActiveTab(tab.tabKey);
                  setSelectedPlan(null); // clear plan selection on tab switch
                }}
              >
                {tab.tabLabel}
              </button>
            ))}
          </div>

          {/* Plans */}
          <div className="plans">
            {filteredPlans?.map((plan, index) => {
              const isSelected =
                selectedPlan?.data === plan.data &&
                selectedPlan?.tabKey === plan.tabKey;
              return (
                <div
                  key={index}
                  className={`plan-card${isSelected ? ' selected' : ''}`}
                  onClick={() => handlePlanSelect(plan)}
                >
                  <h4 className="plan-title">{plan.data}</h4>
                  <p>{plan.price}</p>
                  <span>{plan.validity}</span>
                </div>
              );
            })}
          </div>
        </div>

        {/* Top Up Section */}
        <div className="recharge-card" style={!isPhoneValid ? disabledStyle : {}}>
          <div className="section-header">
            <h3>{props.topUpTitle}</h3>
            <span>${topUpAmount.toFixed(2)}</span>
          </div>

          <div className="plans">
            {props.topUpAmounts?.map((item, index) => {
              const isSelected = selectedTopUp?.amount === item.amount;
              return (
                <div
                  key={index}
                  className={`plan-card${isSelected ? ' selected' : ''}`}
                  onClick={() => handleTopUpSelect(item)}
                >
                  <h4>{item.amount}</h4>
                  <span>{item.label}</span>
                </div>
              );
            })}
          </div>
        </div>

        {/* Payment Section */}
        <div className="recharge-card" style={!isPhoneValid ? disabledStyle : {}}>
          <h3>{props.paymentSectionTitle}</h3>
          <div
            className="payment-description"
            dangerouslySetInnerHTML={{ __html: props.paymentDescription }}
          />
        </div>

        {/* Order Summary */}
        <div className="recharge-card" style={!isPhoneValid ? disabledStyle : {}}>
          <h3>{props.orderSummaryTitle}</h3>

          <div className="summary">
            <span>
              {props.regulatoryFeeLabel} ({props.regulatoryFeePercentage}%)
            </span>
            <span>${fee.toFixed(2)}</span>
          </div>

          <div className="summary total">
            <span>{props.totalAmountLabel}</span>
            <span>${total.toFixed(2)}</span>
          </div>
        </div>

        {/* CTA */}
        <div className="recharge-card" style={!isPhoneValid ? disabledStyle : {}}>
          <div
            className="terms-text"
            dangerouslySetInnerHTML={{ __html: props.termsText }}
          />
          <button className="cta-btn" onClick={handleConfirm}>
            {props.ctaButtonText} ${total.toFixed(2)}
          </button>
        </div>

      </div>
    </div>
  );
};

export default LycaReachargeComponent;
