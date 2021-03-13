import React, {useState} from 'react'
import {FaSearch} from 'react-icons/fa'

const Search = ({cancelMatch, setQuery}) => {

    const [search, setSearch] = useState('')

    const handleFocus = () => {
        cancelMatch()
    }

    const handleChange = (e) => {
        setSearch(e.target.value)
        setQuery(e.target.value)
    }

    const handleSubmit = (e) => {
        e.preventDefault()
        setQuery(e.target.value)
    }

    return (
        <div className="search">
            <form className="form-search" onSubmit={handleSubmit}>
                <div className="search-input">
                    <input type="text" value={search} onChange={handleChange} onFocus={handleFocus} placeholder="Search shopping item ..."/>
                    <FaSearch className="search-icon" size="1rem" onClick={handleSubmit}/>
                </div>
            </form>
            
        </div>
    )
}

export default Search
