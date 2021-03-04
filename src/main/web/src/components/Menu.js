import React, {useState} from 'react';
import {Dropdown} from 'semantic-ui-react';

const Menu = ({sortByEndDate, sortByPrice, sortByName, sortByStore}) => {
    const [menuClicked, setMenuClicked] = useState(false)

    const handleClick = () => {
        setMenuClicked(!menuClicked);
    }

    const handleBlur = () => {
        setMenuClicked(false);
    }

    return (
            <Dropdown text="Sort by" className="dropdown" onClick={handleClick} onBlur={handleBlur}>
                <Dropdown.Menu className={`options ${menuClicked ? 'show' : 'hide'}`}>    
                    <Dropdown.Item className="option" text="End date" onClick={() => {sortByEndDate(); handleClick();}}/>   
                    <Dropdown.Item className="option" text="Item" onClick={() => {sortByName(); handleClick();}}/>   
                    <Dropdown.Item className="option" text="Store" onClick={() => {sortByStore(); handleClick();}}/>   
                    <Dropdown.Item className="option" text="Price" onClick={() => {sortByPrice(); handleClick();}}/>   
                </Dropdown.Menu>
            </Dropdown>    
    )
}

export default Menu
