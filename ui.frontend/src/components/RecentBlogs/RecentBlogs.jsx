import React from "react";
import img1 from "./assets/Aafiya Saba1.jpg";
import img2 from "./assets/Aafiya Saba2.jpg";
import img3 from "./assets/Aafiya Saba3.jpg";
import "./RecentBlogs.css";

const RecentBlogs = (props) => {
  const { headingTitle } = props;

  return (
    <div className="recent-blogs">
      <h2 className="recent-blogs__title">
        {headingTitle || "View our recent blogs"}
      </h2>

      <div className="recent-blogs__cards">

        <div className="blog-card">
          <div className="blog-card__image">
            <img src={img1} alt="blog1" />
            <span className="blog-card__date">18 Jun 2025</span>
          </div>

          <div className="blog-card__content">
            <h3 className="blog-card__heading">
              Best No-Contract Phone Plans With Unlimited Data & Hotspot (2025)
            </h3>
            <p className="blog-card__desc">
              Maecenas interdum turpis vitae consectetur porttitor. Donec vel sem in nulla fringilla pretium non tincidunt mauris.
            </p>

            <div className="blog-card__footer">
              <a href="#" className="blog-card__readmore">Read More →</a>
              <span className="blog-card__author">By Aafiya Saba</span>
            </div>
          </div>
        </div>

        <div className="blog-card">
          <div className="blog-card__image">
          <img src={img2} alt="blog2"/>
            <span className="blog-card__date">18 Jun 2025</span>
          </div>

          <div className="blog-card__content">
            <h3 className="blog-card__heading">
              How To Game With Unlimited High-Speed Data — No Throttling, No
            </h3>
            <p className="blog-card__desc">
              Maecenas interdum turpis vitae consectetur porttitor. Donec vel sem in nulla fringilla pretium non tincidunt mauris.
            </p>

            <div className="blog-card__footer">
              <a href="#" className="blog-card__readmore">Read More →</a>
              <span className="blog-card__author">By Aafiya Saba</span>
            </div>
          </div>
        </div>

        <div className="blog-card">
          <div className="blog-card__image">
           <img src={img3} alt="blog3" />
            <span className="blog-card__date">18 Jun 2025</span>
          </div>

          <div className="blog-card__content">
            <h3 className="blog-card__heading">
              Cheapest Cell Phone Plans With Unlimited Everything In 2025 (No)
            </h3>
            <p className="blog-card__desc">
              Maecenas interdum turpis vitae consectetur porttitor. Donec vel sem in nulla fringilla pretium non tincidunt mauris.
            </p>

            <div className="blog-card__footer">
              <a href="#" className="blog-card__readmore">Read More →</a>
              <span className="blog-card__author">By Aafiya Saba</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default RecentBlogs;

