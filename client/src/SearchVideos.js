import { useState } from "react";

function SearchResults() {
    const [query, setQuery] = useState("");
    const [results, setResults] = useState([]);
    const [currentPage, setCurrentPage] = useState(1); // Current page state
    const resultsPerPage = 10
    // Function to handle form submission
    const handleSearch = (e) => {
        e.preventDefault(); // Prevent page reload

        if (!query.trim()) {
            console.warn("Search query is empty");
            return; // Don't search for empty queries
        }

        fetch(`http://localhost:8080/api/videos/search?query=${query}`)
            .then((res) => res.json())
            .then((data) => {
                console.log("Received Data:", data);
                setResults(Array.isArray(data) ? data.flat() : []);
                setCurrentPage(1);
            })
            .catch((error) => {
                console.error("Error fetching search results:", error);
                setResults([]);
            });
    };

    // Calculate the displayed results for the current page
    const indexOfLastResult = currentPage * resultsPerPage;
    const indexOfFirstResult = indexOfLastResult - resultsPerPage;
    const currentResults = results.slice(indexOfFirstResult, indexOfLastResult);

    // Function to change pages
    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div>
            <form onSubmit={handleSearch}>
                <input
                    type="text"
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                    placeholder="Search for videos..."
                />
                <button type="submit">Search</button>
            </form>

            <div>
                {Array.isArray(results) && results.length > 0 ? (
                    results.map((video) => (
                        <li key={video.id} className="p-2 border-b">
                            <div>Video Name: {video.name}</div>
                            <div>Video Year: {video.year}</div>
                            <div>Video Genre: {Array.isArray(video.genre) ? video.genre.join(" ") : "No genre available"}</div>
                            <div>Video Cast: {Array.isArray(video.cast) ? video.cast.join(" ") : "No cast available"}</div>
                            <div>Video Director: {video.director}</div>
                            <div>Video Description: {video.summaryText}</div>
                            <div>Video Rating Count: {video.ratingCount}</div>
                            <div>Video Rating Value: {video.ratingValue}</div>
                        </li>
                    ))
                ) : (
                    <p>No results found</p>
                )}
            </div>

        {/* Pagination Controls */}
        {results.length > resultsPerPage && (
            <div>
                <button
                    onClick={() => paginate(currentPage - 1)}
                    disabled={currentPage === 1}
                >
                    Prev
                </button>
                <span> Page {currentPage} of {Math.ceil(results.length / resultsPerPage)} </span>
                <button
                    onClick={() => paginate(currentPage + 1)}
                    disabled={currentPage === Math.ceil(results.length / resultsPerPage)}
                >
                    Next
                </button>
            </div>
        )}
        </div>
    );
}

export default SearchResults;