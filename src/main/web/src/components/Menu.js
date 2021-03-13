import React, {useState} from 'react';
import Dropdown from 'react-bootstrap/Dropdown';
import {MdArrowDropDown} from 'react-icons/md'

const Menu = ({sortByEndDate, sortByPrice, sortByName, sortByStore}) => {
    const [menuClicked, setMenuClicked] = useState(false)

    const handleClick = () => {
        setMenuClicked(!menuClicked);
    }

    const handleBlur = () => {
        setMenuClicked(false);
    }

    return (
        <div>
            <Dropdown>
                <Dropdown.Toggle variant="success" id="dropdown-basic" className="dropdown">
                    Sort by <MdArrowDropDown size="1rem"/>
                </Dropdown.Toggle>
                <Dropdown.Menu className="options">
                    <Dropdown.Item className="option" onClick={() => {sortByEndDate(); handleClick();}}>End date</Dropdown.Item>
                    <Dropdown.Item className="option" onClick={() => {sortByName(); handleClick();}}>Name</Dropdown.Item>
                    <Dropdown.Item className="option" onClick={() => {sortByStore(); handleClick();}}>Store</Dropdown.Item>
                    <Dropdown.Item className="option" onClick={() => {sortByPrice(); handleClick();}}>price</Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>

        </div>
    )
}

export default Menu
