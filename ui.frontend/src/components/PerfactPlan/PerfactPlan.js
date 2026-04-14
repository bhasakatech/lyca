import React, { useEffect, useState } from "react";
import "./PerfactPlan.css";
import { Link } from 'react-router-dom';

const PerfactPlan = (props) => {

    const heading = props.heading || (props.data && props.data.heading);

    const questions =
        props.perfectPlanQuestions ||
        (props.data && props.data.perfectPlanQuestions) ||
        [];

    const plans =
        props.plans ||
        (props.data && props.data.plans) ||
        [];

    const [selected, setSelected] = useState({});
    const [plan, setPlan] = useState({});
    const getPlanByName = (name) => {
        return plans.find(p => p.planName === name);
    };

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
        const q1 = answers[0];
        const q2 = answers[1];
        const q3 = answers[2];

        let selectedPlanName = "Essential";

        if (!q1 && !q2 && !q3) {
            selectedPlanName = "Essential";
        }
        else if (q3 === "Family plans") {
            selectedPlanName = "Family Plan";
        }
        else if (q3 === "International calls" || q2 === "Regularly" || q2 === "Very frequently") {
            selectedPlanName = "Premium";
        }
        else if (q3 === "Most data") {
            selectedPlanName = "Premium";
        }
        else if (q3 === "Lowest price") {
            if (q1 === "Light (< 5GB)") {
                selectedPlanName = "Essential";
            } else if (q1 === "Moderate (5–15GB)") {
                selectedPlanName = "Standard";
            } else if (q1) {
                selectedPlanName = "Premium";
            } else {
                selectedPlanName = "Essential";
            }
        }

        // Get plan from CF data
        const selectedPlan = getPlanByName(selectedPlanName);

        if (selectedPlan) {
            setPlan({
                name: selectedPlan.planName,
                data: selectedPlan.data,
                price: selectedPlan.price
            });
        }
    };

    // Set default plan from CF on load
    useEffect(() => {
        if (plans.length > 0) {
            const defaultPlan = getPlanByName("Essential") || plans[0];
            if (defaultPlan) {
                setPlan({
                    name: defaultPlan.planName,
                    data: defaultPlan.data,
                    price: defaultPlan.price
                });
            }
        }
    }, [plans]);

    useEffect(() => {
        console.log("Model JSON:", { heading, questions, plans });
    }, [heading, questions, plans]);

    return (
        <div className="perfact-plan-container">

            {/* Heading */}
            <h2 className="perfact-plan-main-heading">{heading}</h2>

            <div className="perfact-plan-questions-wrapper">

                {/* Questions */}
                {questions.length > 0 ? (
                    questions.map((item, qIndex) => (
                        <div key={qIndex} className="perfact-plan-question-card">

                            <p className="perfact-plan-question-count">
                                Question {qIndex + 1} of {questions.length}
                            </p>

                            <h3 className="perfact-plan-question-text">{item.question}</h3>

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

                {/* Plan Card */}
                <div className="perfact-plan-card">

                    <div className="perfact-plan-icon">{props.icon}</div>

                    <h2 className="perfact-plan-heading">{props.title}</h2>
                    <p className="perfact-plan-subtitle">{props.description}</p>
                    <div className="perfact-plan-box">
                        <span className="perfact-plan-type">{plan.name}</span>
                        <div className="perfact-plan-main">
                            <span className="perfact-plan-data">{plan.data}</span>
                            <span className="perfact-plan-price">
                                {plan.price}
                                <small>/month</small>
                            </span>
                        </div>
                    </div>

                    <button className="perfact-plan-button">
                        <Link to={props.buttonLink} className="perfact-paln-button-label">
                            {props.buttonText}
                        </Link>
                    </button>

                    <Link to={props.othersPlanLink} className="perfact-plan-view-others">
                        {props.othersPlanText}
                    </Link>

                </div>
            </div>
        </div>
    );
};

export default PerfactPlan;