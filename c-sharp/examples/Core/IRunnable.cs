using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace app.Core
{
    public interface IRunnable: IComparable<IRunnable>
    {
        Task RunAsync();
        int Order();
    }
}
