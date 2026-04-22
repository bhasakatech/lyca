import React, { useEffect, useState } from "react";
import "./ApiContentTable.css";

const ApiContentTable = () => {

  const [data, setData] = useState([]);
  const [columns, setColumns] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetch("/bin/lyca/api-data")
      .then((res) => {
        if (!res.ok) {
          throw new Error("Failed to fetch API data");
        }
        return res.json();
      })
      .then((result) => {

        setData(result);

        if (result && result.length > 0) {
          const keys = Object.keys(result[0]);
          setColumns(keys);
        }

        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Loading...</p>;

  if (error) return <p>Error: {error}</p>;

  if (!data || data.length === 0) {
    return <p>No data available</p>;
  }

  return (
    <div className="api-table-container">
      <h2>Dynamic API Data</h2>

      <table className="api-table">
        <thead>
          <tr>
            {columns.map((col, index) => (
              <th key={index}>{col}</th>
            ))}
          </tr>
        </thead>

        <tbody>
          {data.map((item, rowIndex) => (
            <tr key={rowIndex}>
              {columns.map((col, colIndex) => (
                <td key={colIndex}>
                  {typeof item[col] === "object"
                    ? JSON.stringify(item[col])
                    : item[col]}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ApiContentTable;
