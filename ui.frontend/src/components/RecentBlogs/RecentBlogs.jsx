import React, { useState } from "react";
import "./RecentBlogs.css";

const RecentBlogs = ({ blogs = [], headingTitle }) => {
  const [showAll, setShowAll] = useState(false);

  if (!blogs.length) {
    return <div>No blogs available</div>;
  }

  const visibleBlogs = showAll ? blogs : blogs.slice(0, 3);

  return (
    <div className="recent-blogs">
      <h2 className="recent-blogs__title">
        {headingTitle || "View our recent blogs"}
      </h2>

      <div className="recent-blogs__cards">
        {visibleBlogs.map((blog, index) => (
          <div key={index} className="recent-blogs__card">
            <div className="blog-card">

              <div className="blog-card__image">
                <img src={blog.blogImage} alt={blog.blogTitle} />
                <span className="blog-card__date">{blog.publishDate}</span>
              </div>

              <div className="blog-card__content">
                <h3 className="blog-card__heading">{blog.blogTitle}</h3>

                <p
                  className="blog-card__desc"
                  dangerouslySetInnerHTML={{ __html: blog.blogDescription }}
                ></p>
              </div>

              <div className="blog-card__footer">
                <a href={blog.ctaLink} className="blog-card__readmore">
                  {blog.ctaLabel} →
                </a>
                <span className="blog-card__author">
                  By {blog.authorName}
                </span>
              </div>

            </div>
          </div>
        ))}
      </div>

{!showAll && blogs.length > 3 && (
  <div className="recent-blogs__viewmore">
    <span
      className="view-more-link"
      onClick={() => setShowAll(true)}
    >
      View more blogs →
    </span>
  </div>
)}
    </div>
  );
};
export default RecentBlogs;