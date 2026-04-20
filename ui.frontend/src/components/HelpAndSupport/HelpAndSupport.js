import React from "react";
import "./HelpAndSupport.css";

const HelpAndSupport = (props) => {
  const {
    heading,
    subHeading,
    emailSupportLabel,
    responseTime,
    supportSuggestionText,
    supportHoursLabel,
    supportHours,
    emailLabel,
    email,
    requiredInfoLabel,
    requiredInfo,
    mentionNoAccountInfoLabel,
    mentionNoAccountInfo,
    note,
    callSupportLabel,
    customerCareHoursLabel, 
    customerCareHours,
    callOptionsLabel,
    callOptions
  } = props;

  return (
    <div className="help-support">
      {/* Header */}
      <div className="header">
        <h2>{heading}</h2>
        <p>{subHeading}</p>
      </div>

      <div className="cards">
        {/* Email Support */}
        <div className="card">
          <div className="email-support">

            <h3>{emailSupportLabel}</h3>

            <p><strong>{responseTime}</strong></p>
            <p>{supportSuggestionText}</p>

            <h4>{supportHoursLabel}</h4>
            {supportHours?.map((item, i) => (
              <p key={i}>{item}</p>
            ))}

            <h4>{emailLabel}</h4>
            <a href={`mailto:${email}`} className="email-link">
              {email}
            </a>

            <h4>{requiredInfoLabel}</h4>
            {requiredInfo?.map((item, i) => (
              <p key={i}>{item}</p>
            ))}

            <div className="no-account">
              <h4>{mentionNoAccountInfoLabel}</h4>
              <div
                dangerouslySetInnerHTML={{ __html: mentionNoAccountInfo }}
              />
              <p className="note">{note}</p>
            </div>

          </div>
        </div>
        {/* Call Support */}
        <div className="card">
          <div className="call-support">

            <h3 className="call-support__title">
              {callSupportLabel}
            </h3>

            {/* Customer Care Hours */}
            <div className="call-support__section">
              <h4 className="call-support__heading">
                {customerCareHoursLabel}
              </h4>

              {customerCareHours?.map((hour, index) => (
                <p className="call-support__text" key={index}>
                  {hour}
                </p>
              ))}
            </div>

            {/* Call Options */}
            <div className="call-support__section">
              <h4 className="call-support__heading">
                {callOptionsLabel}
              </h4>

              {callOptions?.map((option, index) => (
                <p className="call-support__text" key={index}>
                  <span className="call-support__label">
                    {option.label}:
                  </span>{" "}
                  {option.number}
                </p>
              ))}
            </div>

          </div>
        </div>

      </div>
    </div>
  );
};

export default HelpAndSupport;