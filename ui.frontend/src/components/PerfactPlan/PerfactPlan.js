import React, { useEffect, useState } from "react";
import "./PerfactPlan.css";

const PerfactPlan = (props) => {

    const heading = props.heading || (props.data && props.data.heading);

    const questions =
        props.perfectPlanQuestions ||
        (props.data && props.data.perfectPlanQuestions) ||
        [];

    const [selected, setSelected] = useState({});
    const [plan, setPlan] = useState({ name: "Essential", data: "30GB", price: "£10" });

    // SELECT / DESELECT HANDLER 
    const handleSelect = (qIndex, option) => {
        let updated = { ...selected };

        if (updated[qIndex] === option) {
            delete updated[qIndex]; // deselect
        } else {
            updated[qIndex] = option; // select
        }

        setSelected(updated);
        calculatePlan(updated);
    };

    // CALCULATE PLAN
    const calculatePlan = (answers) => {
        const q1 = answers[0]; // data
        const q2 = answers[1]; // calls
        const q3 = answers[2]; // priority

        // If nothing selected → default
        if (!q1 && !q2 && !q3) {
            setPlan({ name: "Essential", data: "30GB", price: "£10" });
            return;
        }

        // Family (highest priority)
        if (q3 === "Family plans") {
            setPlan({ name: "Family Plan", data: "100GB", price: "£30" });
            return;
        }

        // International OR heavy calling → Premium
        if (
            q3 === "International calls" ||
            q2 === "Regularly" ||
            q2 === "Very frequently"
        ) {
            setPlan({ name: "Premium", data: "Unlimited", price: "£25" });
            return;
        }

        // Most data → Premium
        if (q3 === "Most data") {
            setPlan({ name: "Premium", data: "Unlimited", price: "£25" });
            return;
        }

        // Lowest price → depends on data usage
        if (q3 === "Lowest price") {
            if (q1 === "Light (< 5GB)") {
                setPlan({ name: "Essential", data: "30GB", price: "£10" });
            } else if (q1 === "Moderate (5–15GB)") {
                setPlan({ name: "Standard", data: "60GB", price: "£15" });
            } else if (q1) {
                setPlan({ name: "Premium", data: "Unlimited", price: "£25" });
            } else {
                // if q1 missing
                setPlan({ name: "Essential", data: "30GB", price: "£10" });
            }
            return;
        }
        setPlan({ name: "Essential", data: "30GB", price: "£10" });
    };


    useEffect(() => {
        console.log("Model JSON:", { heading, questions });
    }, [heading, questions]);

    return (
        <div className="perfact-plan-container">
            {/* Heading */}
            <h2 className="perfact-plan-main-heading">{heading}</h2>

            {/* Questions */}
            <div className="perfact-plan-questions-wrapper">
                {questions.length > 0 ? (
                    questions.map((item, qIndex) => (
                        <div key={qIndex} className="perfact-plan-question-card">
                            {/* Question Count */}
                            <p className="perfact-plan-question-count">
                                Question {qIndex + 1} of {questions.length}
                            </p>

                            {/* Question */}
                            <h3 className="perfact-plan-question-text">{item.question}</h3>

                            {/* Options */}
                            <div className="perfact-plan-options">
                                {item.options?.map((opt, i) => {
                                    const isSelected = selected[qIndex] === opt;

                                    return (
                                        <div
                                            key={i}
                                            className={`perfact-plan-option ${isSelected ? "active" : ""}`}
                                            onClick={() => handleSelect(qIndex, opt)}
                                        >
                                            <span>{opt}</span>
                                            {isSelected && <span className="perfact-plan-check">✔</span>}
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
                        <span className="perfact-plan-type">{plan.name}</span>
                        <span className="perfact-plan-data">{plan.data}</span>
                        <span className="perfact-plan-price">{plan.price}<small>/month</small></span>
                    </div>

                    <button class="perfact-plan-button">Get Started</button>
                    <a class="perfact-plan-view-others">View Others Plan</a>
                </div>
            </div>
        </div>
    );
};

export default PerfactPlan;