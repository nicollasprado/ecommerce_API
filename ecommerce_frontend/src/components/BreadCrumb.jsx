import { ChevronRight } from 'lucide-react';

function BreadCrumb( { path, className } ){
    return(
        <ul className="flex gap-2">
            {path.map((fragment, index) =>
              <li key={index} className="flex">
                  <a href="#">{fragment.toString()}</a>
                  {index < path.length - 1 && <ChevronRight />}
              </li>
            )}
        </ul>
    )
}

export default BreadCrumb;
