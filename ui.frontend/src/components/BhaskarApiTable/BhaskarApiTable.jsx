import React, { useEffect, useState } from "react";
import "./BhaskarApiTable.css";

const BhaskarApiTable = () => {

    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    const fetchData = async () => {
        setLoading(true);
        setError("");

        try {
            const res = await fetch("/bin/api/flexible");
            const result = await res.json();

            if (result.error || result.message) {
                setError(result.error || result.message);
                setData(null);
            } else {
                setData(result);
            }

        } catch (err) {
            setError("API call failed");
        }

        setLoading(false);
    };

    useEffect(() => {
        fetchData();
    }, []);

    const renderTable = (data) => {

        if (!data) return null;

        // ARRAY
        if (Array.isArray(data)) {

            if (data.length === 0) {
                return <p className="api-empty">No Data Available</p>;
            }

            const columns = Object.keys(data[0]);

            return (
                <table className="api-table">
                    <thead>
                        <tr>
                            {columns.map((col) => (
                                <th key={col}>{col}</th>
                            ))}
                        </tr>
                    </thead>

                    <tbody>
                        {data.map((row, i) => (
                            <tr key={i}>
                                {columns.map((col) => (
                                    <td key={col}>
                                        {typeof row[col] === "object"
                                            ? JSON.stringify(row[col])
                                            : row[col]}
                                    </td>
                                ))}
                            </tr>
                        ))}
                    </tbody>
                </table>
            );
        }

        // OBJECT
        if (typeof data === "object") {
            return (
                <table className="api-table">
                    <tbody>
                        {Object.entries(data).map(([key, value]) => (
                            <tr key={key}>
                                <td className="api-key">{key}</td>
                                <td className="api-value">
                                    {typeof value === "object"
                                        ? JSON.stringify(value)
                                        : value}
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            );
        }

        return <pre className="api-json">{JSON.stringify(data, null, 2)}</pre>;
    };

    return (
        <div className="api-container">

            <h2 className="api-title">Flexible API Viewer</h2>

            <button className="api-button" onClick={fetchData}>
                Refresh
            </button>

            {loading && <p className="api-loading">Loading...</p>}
            {error && <p className="api-error">{error}</p>}

            {!loading && !error && renderTable(data)}
        </div>
    );
};

export default BhaskarApiTable;