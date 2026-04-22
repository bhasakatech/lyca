import React, { useEffect, useState } from "react";
import Loader from "../Loader/Loader";
import "./RestTableContent.css";

const RestTableContent = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const url = `/bin/lyca/rest-table`;
        const response = await fetch(url, {
          method: "GET",
          credentials: "include",
        });

        const result = await response.json();

        if (!response.ok) {
          throw new Error(`Request failed with status ${response.status}`);
        }

        setData(result);
      } catch (err) {
        console.error("Error fetching table data:", err);
        setError(err.message || "Something went wrong.");
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) return <Loader message="Please Wait...." />;
  
  if (error) {
    return (
      <div className="rtc-table-page">
        <div className="rtc-table-error">{error}</div>
      </div>
    );
  }

  if (!data || data.length === 0) {
    return (
      <div className="rtc-table-page">
        <div className="rtc-table-error">No Data available.</div>
      </div>
    );
  }

  const headers = Object.keys(data[0]);

  const renderCellValue = (value) => {
    if (value === null || value === undefined || value === "") return "-";
    
    // React doesn't render boolean values by default, so convert them to strings
    if (typeof value === "boolean") {
      return value ? "True" : "False";
    }

    if (typeof value === "object") {
      return JSON.stringify(value);
    }

    if (typeof value === "string") {
      // Auto-detect image paths/URLs and render them as small thumbnail images
      if (/\.(jpeg|jpg|gif|png|svg|webp)(\?.*)?$/i.test(value) || value.startsWith("data:image/")) {
        return (
          <img 
            src={value} 
            alt="table thumbnail" 
            className="rtc-table-image" 
          />
        );
      }
    }

    return value;
  };

  return (
    <div className="rtc-table-page">
      <div className="rtc-table-wrapper">
        <table className="rtc-rest-table">
          <thead>
            <tr>
              {headers.map((key) => (
                <th key={key}>{key}</th>
              ))}
            </tr>
          </thead>

          <tbody>
            {data.map((item, rowIndex) => (
              <tr key={rowIndex}>
                {headers.map((key) => (
                  <td key={key} data-label={key}>
                    {renderCellValue(item[key])}
                  </td>
                ))}
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default RestTableContent;