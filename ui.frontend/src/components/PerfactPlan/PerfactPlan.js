import React, { useEffect, useState } from "react";
import "./PerfactPlan.css";

const PerfactPlan = (props) => {
   
    const heading = props.heading || (props.data && props.data.heading);

    const questions =
        props.perfectPlanQuestions ||
        (props.data && props.data.perfectPlanQuestions) ||
        [];

    const [selected, setSelected] = useState({});

    const handleSelect = (qIndex, option) => {
        setSelected((prev) => ({
            ...prev,
            [qIndex]: option,
        }));
    };

    useEffect(() => {
        console.log("Model JSON:", { heading, questions });
    }, [heading, questions]);

    return (
        <div className="perfact-plan-container">
            {/* Heading */}
            <h2 className="perfact-plan-main-heading">{heading}</h2>

            {/* Questions */}
            <div className="questions-wrapper">
                {questions.length > 0 ? (
                    questions.map((item, qIndex) => (
                        <div key={qIndex} className="question-card">
                            {/* Question Count */}
                            <p className="question-count">
                                Question {qIndex + 1} of {questions.length}
                            </p>

                            {/* Question */}
                            <h3 className="question-text">{item.question}</h3>
 
                            {/* Options */}
                            <div className="options">
                                {item.options?.map((opt, i) => {
                                    const isSelected = selected[qIndex] === opt;

                                    return (
                                        <div
                                            key={i}
                                            className={`option ${isSelected ? "active" : ""}`}
                                            onClick={() => handleSelect(qIndex, opt)}
                                        >
                                            <span>{opt}</span>
                                            {isSelected && <span className="check">✔</span>}
                                        </div>
                                    );
                                })}

                            </div>
                        </div>
                    ))
                ) : (
                    <p>No questions available</p>
                )}

                <div className="perfact-plan-card">
                    <div className="perfact-plan-icon">📊</div>

                    <h2 class="perfact-plan-heading">The best plan for you</h2>
                    <p className="perfact-plan-subtitle">Based on your answers, we recommend:</p>

                    <div className="perfact-plan-box">
                        <span className="perfact-plan-type">Essential</span>
                        <span className="perfact-plan-data">30GB</span>
                        <span className="perfact-plan-price">£10 <small>/month</small></span>
                    </div>

                   <button class="perfact-plan-button">Get Started</button>
                   <a class="perfact-plan-view-others">View Others Plan</a>
                </div>
            </div>
        </div>
    );
};

export default PerfactPlan;